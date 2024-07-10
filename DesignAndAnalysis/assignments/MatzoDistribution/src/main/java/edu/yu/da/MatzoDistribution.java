package edu.yu.da;

import java.util.*;

public class MatzoDistribution extends MatzoDistributionBase {

    private class Node{
        private final String wareHouseId;
        private int edge;
        private Node correspondingEdge;
        private Node parent;

        public Node(String wareHouse, int edge){
            this.edge = edge;

            this.wareHouseId = wareHouse;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return wareHouseId.equals( node.wareHouseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(wareHouseId);
        }
    }


    /**
     * Constructor: defines the two "endpoints" of the distribution network.
     *
     * @param sourceWarehouse      names the warehouse that initiates the matzo
     *                             distribution, cannot be blank, must differ from destinationWarehouse.
     * @param sourceConstraint     positive-valued-integer representing an upper
     *                             bound on the amount of matzo packages that can be distributed per day from
     *                             the source warehouse.
     * @param destinationWarehouse names the warehouse to which all matzos must
     *                             ultimately be delivered, cannot be blank, must differ from sourceWarehouse.
     * @throws IllegalArgumentException if the parameter pre-conditions are not
     *                                  met.
     */
    private final Map<String,LinkedList<Node>> graph;
    private final Set<String> wareHouses;
    private int minConstraint;
    private final String root;
    private final String tap;
    private int currentFlow;



    public MatzoDistribution(String sourceWarehouse, int sourceConstraint, String destinationWarehouse) {
        super(sourceWarehouse, sourceConstraint, destinationWarehouse);
        validateConstructor(sourceWarehouse,sourceConstraint,destinationWarehouse);
        graph = new HashMap<>();
        wareHouses = new HashSet<>();
        this.root = sourceWarehouse;
        this.tap = destinationWarehouse;

        graph.put(sourceWarehouse,new LinkedList<>());
        graph.put(destinationWarehouse,new LinkedList<>());
        LinkedList list = new LinkedList();
        Node node = new Node(destinationWarehouse,Integer.MAX_VALUE);

        node.correspondingEdge = new Node(this.root+"temp",0);
        list.add(node);
        graph.put(destinationWarehouse+"temp",list);

        wareHouses.add(this.root);
        wareHouses.add(tap);
        wareHouses.add(destinationWarehouse+"temp");

        minConstraint = sourceConstraint;
        currentFlow = 0;
    }

    private void validateConstructor(String sourceWarehouse, int sourceConstraint, String destinationWarehouse) {
        if(sourceWarehouse == null || destinationWarehouse == null || sourceWarehouse.isBlank() || destinationWarehouse.isBlank() || sourceWarehouse.equals(destinationWarehouse) || sourceConstraint <= 0)
            throw  new IllegalArgumentException();
    }

    @Override
    public void addWarehouse(String warehouseId, int constraint) {
        validateAddWareHouse(warehouseId,constraint);
        this.graph.put(warehouseId, new LinkedList<>());
        LinkedList<Node> list = new LinkedList<>();

        Node node = new Node(warehouseId,constraint);
        node.correspondingEdge  = new Node(warehouseId+"temp",0);
        list.add(node);
        this.graph.put(warehouseId+"temp",list);

        this.wareHouses.add(warehouseId);
        this.wareHouses.add(warehouseId+"temp");

    }

    private void validateAddWareHouse(String warehouseId, int constraint) {
        if(warehouseId == null  ||this.graph.containsKey(warehouseId) || constraint <= 0 || warehouseId.isBlank())
            throw new IllegalArgumentException();
    }

    @Override
    public void roadExists(String w1, String w2, int constraint) {
        validateRoadExists(w1,w2,constraint);


        LinkedList<Node> list1 = this.graph.get(w1);


        Node fromW1ToTemp = new Node(w2+"temp",constraint);
        Node fromTempToW1 = new Node(w1,0);



        fromW1ToTemp.correspondingEdge = fromTempToW1;
        fromTempToW1.correspondingEdge = fromW1ToTemp;



        list1.add(fromW1ToTemp);


    }

    private void validateRoadExists(String w1, String w2, int constraint) {
        if(w1 == null || w2 == null || w1.isBlank() || w2.isBlank() || constraint<=0|| !this.graph.containsKey(w1) || !this.graph.containsKey(w2)||  w1.equals(this.tap) )
            throw new IllegalArgumentException();
    }

    @Override
    public int max() {
        List<Node> flowPath = breadthFirstSearch();
        while(flowPath != null){
            int bottleNeck = this.minConstraint;
            for (Node currentNode : flowPath) {
                int constraint2 = currentNode.edge;
                bottleNeck = Integer.min(bottleNeck, constraint2);
            }
            for (Node currentNode : flowPath) {
                currentNode.edge = currentNode.edge - bottleNeck;
                currentNode.correspondingEdge.edge = currentNode.correspondingEdge.edge + bottleNeck;
            }
            this.minConstraint -= bottleNeck;

            currentFlow += bottleNeck;
            flowPath = breadthFirstSearch();
        }
        return currentFlow ;
    }

    private List<Node> breadthFirstSearch() {
        if(this.minConstraint == 0)
            return null;
        Queue<Node> queue = new LinkedList<>();
        Set<String> explored = new HashSet<>();
        for(Node node:this.graph.get(this.root)) {
            if (node.edge != 0){
                queue.add(node);
                explored.add(node.wareHouseId);
            }
        }
        List<Node> bfs = new ArrayList<>();

        Node currentWarehouse;
        while (!queue.isEmpty()){
            currentWarehouse = queue.remove();
                for(int i =0; i<this.graph.get(currentWarehouse.wareHouseId).size(); i++){
                    Node currentNode = this.graph.get(currentWarehouse.wareHouseId).get(i);
                    if(currentNode.edge != 0  && !explored.contains(currentNode.wareHouseId)){
                        if(currentNode.wareHouseId.equals(this.tap)){
                            currentNode.parent = currentWarehouse;
                            Node temp = currentNode;
                            while(temp.parent != null){
                                bfs.add(temp);
                                temp = temp.parent;
                            }
                            bfs.add(temp);
                            return bfs;
                        }
                        queue.add(currentNode);
                        currentNode.parent = currentWarehouse;
                        explored.add(currentNode.wareHouseId);
                    }
                }
        }

        return null;
    }
}
