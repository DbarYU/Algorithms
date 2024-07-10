package edu.yu.da;

import java.util.*;

public class WaterPressure extends WaterPressureBase {

    private class Node implements Comparable<Node> {
        private final double weight;
        private final String vertex;
        private Node(String vertex,double weight){
            this.vertex = vertex;
            this.weight = weight;
        }


        @Override
        public int compareTo(Node o) {
            return Double.compare(this.weight,o.weight);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(vertex);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return node.vertex.equals(vertex);
        }
    }
    /**
     * Constructor which supplies the initial input pump.
     *
     * @param initialInputPump@throws IllegalArgumentException if the pre-conditions are violated.
     */
    private final HashMap<String,LinkedList<Node>> adjacency_map;
    private final String initialInputPump;
    private String secondInputPump;
    private boolean wasMinAmountInvoked;
    private boolean hasSecondPump;
    private HashMap<String,HashSet<String>> validator;
    private Set<String> allNodes;

    public WaterPressure(String initialInputPump) {
        super(initialInputPump);
        //VALIDATE
        if(initialInputPump.isEmpty())
            throw new IllegalArgumentException();
        this.adjacency_map = new HashMap<>();
        this.adjacency_map.put(initialInputPump,new LinkedList<>());
        this.initialInputPump = initialInputPump;
        this.secondInputPump = null;
        this.wasMinAmountInvoked = false;
        this.hasSecondPump = false;
        this.validator = new HashMap<>();
        this.allNodes = new HashSet<>();
        allNodes.add(initialInputPump);
    }

    /**
     * Adds a second input pump, differing from the initial input pump, to the
     * channel system.
     * <p>
     * The second input pump must already be in the channel system (via
     * addBlockage): this method only designates the pump as also being an input
     * pump.
     *
     * @param secondInputPump@throws IllegalArgumentException if the pre-conditions are violated.
     */
    @Override
    public void addSecondInputPump(String secondInputPump) {
    //VALIDATE
        if(hasSecondPump)
            throw new IllegalStateException();
        if(secondInputPump.isEmpty())
            throw new IllegalArgumentException();
        if(!allNodes.contains(secondInputPump))
            throw new IllegalArgumentException();
        this.secondInputPump = secondInputPump;
        this.hasSecondPump = true;
    }

    /**
     * Specifies a blockage on a channel running from pump station v to pump
     * station w.  The presence of a blockage implies that water can only flow on
     * the channel if a quantity of water greater or equal to "blockage" is
     * pumped by pump station v to w.
     * <p>
     * The two pump stations must differ from one another, and no channel can
     * already exist between the two pump stations.
     *
     * @param v        specifies a pump station, length must be > 0.
     * @param w        specifies a pump station, length must be > 0.
     * @param blockage the magnitude of the blockage on the channel, must be > 0.
     * @throws IllegalStateException    if minAmount() has previously been invoked.
     * @throws IllegalArgumentException if the other pre-conditions are violated.
     */
    @Override
    public void addBlockage(String v, String w, double blockage) {
        validateVertexAndWeight(v,w,blockage);
        addEdges(v, w, blockage);
    }
    private void addEdges(String v, String w, double weight) {
        if (wasMinAmountInvoked) {
            throw new IllegalStateException();
        }
        validateVertexAndWeight(v, w, weight);

        adjacency_map.computeIfAbsent(v, k -> new LinkedList<>()).add(new Node(w, weight));
        validator.computeIfAbsent(v, k -> new HashSet<>()).add(w);
        allNodes.add(v);
        allNodes.add(w);
        adjacency_map.computeIfAbsent(w, k -> new LinkedList<>());
    }

    private void validateVertexAndWeight(String v, String w, double weight) {
        if(wasMinAmountInvoked)
            throw new IllegalStateException();
        if(v == null)
            throw new IllegalArgumentException();
        if(v.isEmpty())
            throw new IllegalArgumentException();
        if(w == null)
            throw new IllegalArgumentException();
        if(w.isEmpty())
            throw new IllegalArgumentException();
        if(weight <= 0)
            throw new IllegalArgumentException();
        if(w.equals(v))
            throw new IllegalArgumentException();
        if(this.validator.get(v)!= null && this.validator.get(v).contains(w))
            throw new IllegalArgumentException();

    }

    /**
     * Client asks implementation to determine the minimum amount of water that
     * must be supplied to the initial input pump to ensure that water reaches
     * every pump station in the existing channel system.  If a second input pump
     * has been added to the channel system, the sematics of "minimum amount" is
     * the "minimum amount of water that must be supplied to BOTH input pump
     * stations".
     *
     * @return the minimum amount of water that must be supplied to the input
     * pump(s) to ensure that water reaches every pump station.  If the channel
     * system has been misconfigured such that no amount of water pumped from the
     * input pump stations can get water to all the pump stations, returns -1.0
     * as a sentinel value.
     */
    @Override
    public double minAmount() {
        this.wasMinAmountInvoked = true;
        return primsAlgorithm();
    }
    private double primsAlgorithm(){
        double maxAmountOfFlow = 0;
        Set<String> visitedVertexes = new HashSet<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(adjacency_map.keySet().size());

        visitedVertexes.add(this.initialInputPump);

        relaxEdges(this.initialInputPump,priorityQueue,visitedVertexes);
        if(this.hasSecondPump) {
            visitedVertexes.add(this.secondInputPump);
            relaxEdges(this.secondInputPump,priorityQueue,visitedVertexes);
        }

        while(visitedVertexes.size() < adjacency_map.keySet().size()){
            String vertex;
            while(true){
                if(priorityQueue.peek() == null)
                    return -1;

                if(visitedVertexes.contains(priorityQueue.peek().vertex))
                    priorityQueue.remove();
                else{
                    Node node = priorityQueue.remove();
                    maxAmountOfFlow = Double.max(maxAmountOfFlow,node.weight);
                    visitedVertexes.add(node.vertex);
                    vertex = node.vertex;
                    break;
                }
            }
            if(vertex == null)
                return -1;

            relaxEdges(vertex,priorityQueue,visitedVertexes);

            //relax all edges coming from firstPump
            //relax all edges coming from secondPump
            //remove the smallest node that connects to the three sub graphs.

        }
        return maxAmountOfFlow;

    }

    private void relaxEdges(String vertex, PriorityQueue<Node> priorityQueue,Set<String> visitedVertexes) {
            for(Node node:this.adjacency_map.get(vertex)) {
                if (!visitedVertexes.contains(node.vertex)) {
                    priorityQueue.add(node);
                }
            }
    }



}
