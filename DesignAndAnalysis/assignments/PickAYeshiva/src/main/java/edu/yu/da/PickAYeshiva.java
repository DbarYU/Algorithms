package edu.yu.da;

import java.util.*;
import java.util.stream.Collectors;

public class PickAYeshiva extends PickAYeshivaBase {
    private class Yeshiva implements Comparable<Yeshiva> {
        private final double cookRank;
        private final double ratioRank;
        public Yeshiva(double cookRank,double ratioRank){
            this.cookRank = cookRank;
            this.ratioRank = ratioRank;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Yeshiva yeshiva = (Yeshiva) o;
            return cookRank == yeshiva.cookRank && ratioRank == yeshiva.ratioRank;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cookRank, ratioRank);
        }

        @Override
        public int compareTo(Yeshiva o) {
            if(o.cookRank == cookRank)
                return (int) (ratioRank - o.ratioRank);

            return (int) (cookRank- o.cookRank);
        }
    }

    /**
     * Constructor which supplies the yeshiva rankings in terms of two factors
     * of interest.  The constructor executes a divide-and-conquer algorithm to
     * determine the minimum number of yeshiva-to-yeshiva comparisons required to
     * make a "which yeshiva to attend" decision.  The getters can be accessed in
     * O(1) time after the constructor executes successfully.
     * <p>
     * It is the client's responsibility to ensure that no pair of
     * facultyRatioRankings and cookingRankings values are duplicates.
     *
     * @param facultyRatioRankings Array whose ith element is the value of the
     *                             ith yeshiva with respect to its faculty-to-student ratio (Rabbeim etc).
     *                             Client maintains ownership.  Can't be null and must be same length as the
     *                             other parameter.
     * @param cookingRankings      Array whose ith element is the value of the ith
     *                             yeshiva with respect to the quality of the cooking.  Client maintains
     *                             ownership.  Can't be null and must be same length as other parameter.
     * @throws IllegalArgumentException if pre-conditions are violated.
     */
    private final List<Yeshiva> yeshivas;
    private double[] returnRatio;
    private double[] returnCook;

    public PickAYeshiva(double[] facultyRatioRankings, double[] cookingRankings) {
        super(facultyRatioRankings,cookingRankings);
        validate(facultyRatioRankings,cookingRankings);

        this.yeshivas = addToList(facultyRatioRankings,cookingRankings);
        divideAndConquer(0, facultyRatioRankings.length -1);
        yeshivas.removeIf(yeshiva -> yeshiva.cookRank == -1 && yeshiva.ratioRank == -1);
        returnRatio =  new double[yeshivas.size()];
        returnCook =  new double[yeshivas.size()];

        returnRatio = yeshivas.stream().mapToDouble(y -> y.ratioRank).toArray();
        returnCook = yeshivas.stream().mapToDouble(y -> y.cookRank).toArray();

    }

    private List<Yeshiva> addToList(double[] facultyRatioRankings, double[] cookingRankings) {
        List<Yeshiva> yeshivas = new ArrayList<>();
        for (int i = 0; i < facultyRatioRankings.length; i++) {
            yeshivas.add(new Yeshiva(cookingRankings[i], facultyRatioRankings[i]));
        }
        Collections.sort(yeshivas);

        return yeshivas;
    }


    private double divideAndConquer(int startOfArray,int finishOfArray) {
        if (finishOfArray - startOfArray == 0)
            return yeshivas.get(startOfArray).ratioRank;

        int middle = (startOfArray + finishOfArray) / 2;

        double maxLeft = divideAndConquer(startOfArray, middle);
        double maxRight = divideAndConquer(middle + 1, finishOfArray);

        if(maxRight >= maxLeft) {
            for (int i = startOfArray; i<= middle; i++) {
                this.yeshivas.set(i, new Yeshiva(-1, -1));
            }
            return maxRight;
        }

        for(int i = startOfArray; i<= middle; i++){
            if(this.yeshivas.get(i).ratioRank <= maxRight)
                this.yeshivas.set(i,new Yeshiva(-1,-1));
        }

        return Double.max(maxLeft,maxRight);

    }


    private void validate(double[] facultyRatioRankings,double[] cookingRankings){
        if(facultyRatioRankings == null || cookingRankings == null)
            throw new IllegalArgumentException();

        if(facultyRatioRankings.length == 0 || cookingRankings.length == 0)
            throw new IllegalArgumentException();

        if(facultyRatioRankings.length != cookingRankings.length)
            throw new IllegalArgumentException();

        for(int i =0; i<facultyRatioRankings.length; i++){
            if(facultyRatioRankings[i] < 0  || cookingRankings[i] < 0)
                throw new IllegalArgumentException();
        }

    }
    @Override
    public double[] getFacultyRatioRankings() {
        return this.returnRatio;

    }

    @Override
    public double[] getCookingRankings() {
        return this.returnCook;
    }
}
