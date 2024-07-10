package edu.yu.da;

import java.security.InvalidKeyException;
import java.util.*;

public class SpheresOfInfluence extends SpheresOfInfluenceBase {
    private class Range implements Comparable<Range> {
        private final double start;
        private final double finish;
        private final String ID;

        public Range(final double start, final double finish,String id) {
            this.finish = finish;
            this.start = start;
            this.ID = id;
        }

        @Override
        public int compareTo(Range o) {
            if(start - o.start == 0)
                return (int) (o.finish-finish);

            return (int) (start- o.start);
        }
    }
    private class Node implements Comparable<Node>{

        private final double finish;
        private final String ID;
        public Node(String id,double finish) {
            this.finish = finish;
            this.ID = id;

        }

        @Override
        public int compareTo(Node o) {
            return (int) (o.finish-finish);
        }
    }
    private class Validator {

        private final int xValue;
        private final int radius;
        public Validator(int xValue,int radius) {
            this.xValue = xValue;
            this.radius = radius;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Validator validator = (Validator) o;
            return xValue == validator.xValue && radius == validator.radius;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xValue, radius);
        }
    }

    /**
     * Constructor that defines the MU rectangular 2D plane of student values.
     *
     * @param maxStrength the maximum "strength" value demarcating the MU 2D
     *                    plane in one dimension, must be greater than 0.
     * @param maxRight    the maximum "right religiosity" value (in MU's "left to
     *                    right" spectrum) demarcating the MU 2D plane in the other dimension, must
     *                    be greater than 0.
     */
    private final int maxStrength;
    private final int maxRight;
    private List<String> influencers;
    private PriorityQueue<Range> ranges;
    private Set<String> idValidator;
    private Set<Validator> validator ;

    public SpheresOfInfluence(int maxStrength, int maxRight) {
        super(maxStrength, maxRight);
        this.maxRight =maxRight;
        this.maxStrength = maxStrength;
        this.influencers = new ArrayList<>();
        this.ranges = new PriorityQueue<>();
        this.validator = new HashSet<>();
        this.idValidator = new HashSet<>();


        if(maxRight <= 0 || maxStrength<= 0)
            throw new IllegalArgumentException();
    }

    @Override
    public void addInfluencer(String id, int xValue, int radius) {
        validateInfluencer(id,xValue,radius);
        influencers.add(id);
        this.validator.add(new Validator(xValue,radius));
        this.idValidator.add(id);
        double[] intersections = calculateIntersection(radius,xValue);

        if(intersections == null)
            return;

        Range range = new Range(intersections[0],intersections[1],id);
        this.ranges.add(range);

    }

    private void validateInfluencer(String id, int xValue, int radius) {
        if(id == null)
            throw new IllegalArgumentException();
        if(id.isEmpty())
            throw new IllegalArgumentException();
        if(this.idValidator.contains(id))
            throw new IllegalArgumentException();
        if(xValue <0)
            throw  new IllegalArgumentException();
        if(radius<= 0 )
            throw new IllegalArgumentException();
        if(this.validator.contains(new Validator(xValue,radius)))
            throw new IllegalArgumentException();
    }

    @Override
    public List<String> getMinimalCoverageInfluencers() {
        if(this.influencers.isEmpty())
            return Collections.emptyList();
        List<String> returned = new ArrayList<>();
        Range starting = ranges.remove();
        double start = starting.start;
        double finish = starting.finish;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        returned.add(starting.ID);

        if(start > 0)
            return Collections.emptyList();

        while(!this.ranges.isEmpty() && finish < this.maxRight){
            boolean hasBeenAdded = true;
            while(this.ranges.peek() != null && this.ranges.peek().start <= finish){
                Range range = this.ranges.remove();
                priorityQueue.add(new Node(range.ID,range.finish));
                hasBeenAdded = false;
            }
            if(hasBeenAdded)
                return Collections.emptyList();

            Node removed = priorityQueue.remove();
            returned.add(removed.ID);
            finish = removed.finish;
        }

        if(Double.isNaN(start) && Double.isNaN(finish)){
            return Collections.emptyList();
        }
        else if(finish < this.maxRight)
            return Collections.emptyList();

        Collections.sort(returned);
        return returned;
    }
    private double[] calculateIntersection(int radius,int xValue){
        //currently have function (x-xValue)^2 + (y-maxStrength/2)^2 = radius ^ 2
        //need to find the x values of the function when Y == maxStrength
        //(x-xValue)^2  =  radius ^ 2 - (y-maxStrength/2)^2
        //x - xValue = sqrt(radius ^2 - (y-maxStrength/2)^2)
        //x = +sqrt(radius ^2 - (y-maxStrength/2)^2) + xValue
        //x = -sqrt(radius ^2 - (y-maxStrength/2)^2) + value

        double squareRoot1 = Math.sqrt( (radius*radius) - ( (double)maxStrength/2) * ( (double)maxStrength/2) );
        if(Double.isNaN(squareRoot1))
            return null;

        double result1 = -squareRoot1 + xValue;
        double result2 = squareRoot1+xValue;
        if(result2 < 0 )
            result2 = 0;
        if(result1 < 0)
            result1 = 0;

        return new double[]{result1,result2};
    }

}
