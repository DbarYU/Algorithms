package edu.yu.da;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.*;

public class ThereAndBackAgain extends ThereAndBackAgainBase {

    private class Edge{
        private String vertex;
        private double weight;
        private Edge(String vertex,double weight){
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return edge.vertex.equals(this.vertex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, weight);
        }
    }
    private class Path {
        private List<String> path;
        private final double weight;
        private final String vertex;

        private Path(String vertex, Double weight, List<String> path) {
            this.path = path;
            this.vertex = vertex;
            this.weight = weight;
        }
    }

        private class Node implements Comparable<Node>{
        private String vertex;
        private double pathWeight;
        
        private List<String> precedingVertex;
        private Node(String vertex,double pathWeight){
            this.vertex = vertex;
            this.pathWeight = pathWeight;
            this.precedingVertex = new ArrayList<>();
        }


        private void addPrecedingNode(String str){
            if(!(precedingVertex.size() > 2))
                precedingVertex.add(str);
        }

        @Override
        public int compareTo(Node node) {
            return Double.compare(this.pathWeight,node.pathWeight);

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return node.vertex.equals(this.vertex);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.vertex);
        }

        public void clearPrecedingNodes(String vertex) {
            this.precedingVertex = new ArrayList<>();
            this.precedingVertex.add(vertex);
        }
    }
    
    /**
     * Constructor which supplies the start vertex
     *
     * @param startVertex@throws IllegalArgumentException if the pre-condiitions are
     *                violated
     */
    private final HashMap<String,LinkedList<Edge>> adjacency_map;
    private final String startVertex;
    private boolean doIt;
    private Path validPath1;
    private Path validPath2;
    private double  goalWeight;
    private boolean isTABA;

    public ThereAndBackAgain(String startVertex) {
        super(startVertex);
        if(startVertex.isEmpty())
            throw new IllegalArgumentException();

        this.adjacency_map = new HashMap<>();
        this.startVertex = startVertex;
        this.doIt = false;
        this.goalWeight = 0;
        this.adjacency_map.put(startVertex,new LinkedList<Edge>());
    }

    /**
     * Adds an weighted undirected edge between vertex v and vertex w.  The two
     * vertices must differ from one another, and an edge between the two
     * vertices cannot have been added previously.
     *
     * @param v      specifies a vertex, length must be > 0.
     * @param w      specifies a vertex, length must be > 0.
     * @param weight the edge's weight, must be > 0.
     * @throws IllegalStateException    if doIt() has previously been invoked.
     * @throws IllegalArgumentException if the other pre-conditions are violated.
     */
    @Override
    public void addEdge(String v, String w, double weight) {
        validate(v,w,weight);
        addEdges(v, w, weight);
        addEdges(w, v, weight);
    }
    private void addEdges(String v, String w, double weight) {
        LinkedList<Edge> linkedList;
        if (!this.adjacency_map.containsKey(v)) {
            linkedList = new LinkedList<>();
        } else {
            linkedList = this.adjacency_map.get(v);
        }
        linkedList.add(new Edge(w, weight));
        this.adjacency_map.put(v, linkedList);
    }

    private void validate(String v, String w, double weight) {
        if(doIt)
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

        if(this.adjacency_map.containsKey(w) && this.adjacency_map.get(w).contains(new Edge(v,0)))
            throw new IllegalArgumentException();
    }

    /**
     * Client informs implementation that the graph is fully constructed and
     * that the ThereAndBackAgainBase algorithm should be run on the graph.
     * After the method completes, the client is permitted to invoke the
     * solution's getters.
     * <p>
     * Note: once invoked, the implementation must ignore subsequent calls to
     * this method.
     *
     * @throws IllegalStateException if doIt() has previously been invoked.
     */
    @Override
    public void doIt() {
        if(doIt)
            throw new IllegalArgumentException();

         shortestPathFromStart();
          this.doIt = true;
    }



    private void shortestPathFromStart(){
        //Initialize all vertex aside from starting vertex to be infinite
        IndexMinPQ<Node> heap = new IndexMinPQ<>(this.adjacency_map.keySet().size());
        HashMap<String,Integer> indexMap = new HashMap<>();
        Map<String,Node> vertices = new HashMap<>();
        Map<String,String> precedingNodes = new HashMap<>();
        Set<Node> thereAndBackNodes = new HashSet<>();

        initializeHeap(this.startVertex,heap, indexMap);
        while (!heap.isEmpty()) {
            Node node = heap.minKey();
            heap.delMin();
            vertices.put(node.vertex,node);
            if (node.precedingVertex != null && node.precedingVertex.size() == 2)
                thereAndBackNodes.add(node);
            relaxEdges(heap, indexMap, node,vertices,precedingNodes,thereAndBackNodes);
        }
        for(String vertex: indexMap.keySet()){
            if(vertices.get(vertex).pathWeight == Double.MAX_VALUE){
                thereAndBackNodes.remove(new Node(vertex,0));
                vertices.remove(vertex);
                precedingNodes.remove(vertex);
            }
        }
        if(!thereAndBackNodes.isEmpty())
            this.isTABA = true;

        HashMap<String,Path[]> paths = new HashMap<>();
        for(Node vertex:thereAndBackNodes){
            if(vertex.pathWeight == Double.MAX_VALUE)
                break;

            List<String> path1 = findPathFromStartNode(vertex.precedingVertex.get(0),precedingNodes);
            List<String> path2 = findPathFromStartNode(vertex.precedingVertex.get(1),precedingNodes);
            path1.add(vertex.vertex);
            path2.add(vertex.vertex);
            Path validPath1 = new Path(vertex.vertex,vertex.pathWeight,path2);
            Path validPath2 = new Path(vertex.vertex,vertex.pathWeight,path1);

            paths.put(vertex.vertex,new Path[]{validPath1,validPath2});
            if(vertex.pathWeight > this.goalWeight){
                goalWeight = vertex.pathWeight;
                this.validPath1 = validPath1;
                this.validPath2 = validPath2;
            }
        }
        for(Node vertex:vertices.values() ){
            if(vertex.pathWeight == Double.MAX_VALUE)
                break;
            if(!thereAndBackNodes.contains(vertex) && vertex.pathWeight > this.goalWeight){
                List<String> pathUntilPivot = isPathTABA(vertex.vertex,precedingNodes,thereAndBackNodes);
                if(pathUntilPivot != null) {
                    this.goalWeight = vertex.pathWeight;
                    String pivot = pathUntilPivot.remove(pathUntilPivot.size()-1);

                    List<String> pathFromSource1 = new ArrayList<>(paths.get(pivot)[0].path);
                    pathFromSource1.addAll(pathUntilPivot);

                    List<String> pathFromSource2 = new ArrayList<>(paths.get(pivot)[1].path);
                    pathFromSource2.addAll(pathUntilPivot);


                    this.validPath2 = new Path(vertex.vertex,vertex.pathWeight,pathFromSource2);
                    this.validPath1 = new Path(vertex.vertex,vertex.pathWeight,pathFromSource1);

                }
            }
        }
    }

    private void initializeHeap(String startVertex, IndexMinPQ<Node> heap, HashMap<String, Integer> indexMap) {
        int i = 0;
        for(String vertex:this.adjacency_map.keySet()){
            heap.insert(i,new Node(vertex,Double.MAX_VALUE));
            indexMap.put(vertex,i);
            i++;
        }
        heap.decreaseKey(indexMap.get(startVertex),new Node(startVertex,0.0));

    }
    private void relaxEdges(IndexMinPQ<Node> heap, HashMap<String, Integer> indexMap, Node previousVertex, Map<String, Node> vertices, Map<String, String> precedingNodes, Set<Node> thereAndBackNodes) {
        double distanceToPredeceasingVertex = previousVertex.pathWeight;
        for (Edge edge : this.adjacency_map.get(previousVertex.vertex)) {
            int index = indexMap.get(edge.vertex);
            double newDistanceToVertex = edge.weight;
            double currentDistanceToVertex;
            if (heap.contains(index)) {
                currentDistanceToVertex = heap.keyOf(indexMap.get(edge.vertex)).pathWeight;
                if (distanceToPredeceasingVertex + newDistanceToVertex < currentDistanceToVertex) {
                    Node vertex = new Node(edge.vertex,distanceToPredeceasingVertex + newDistanceToVertex);
                    vertex.clearPrecedingNodes(previousVertex.vertex);
                    heap.decreaseKey(index,vertex);
                    precedingNodes.put(edge.vertex,previousVertex.vertex);
                }else if(currentDistanceToVertex == distanceToPredeceasingVertex + newDistanceToVertex) {
                    Node vertex = heap.keyOf(index);
                    vertex.addPrecedingNode(previousVertex.vertex);
                    precedingNodes.put(edge.vertex,previousVertex.vertex);
                }
            }
            else {
                Node vertex = vertices.get(edge.vertex);
                currentDistanceToVertex = vertex.pathWeight;
                if(currentDistanceToVertex == distanceToPredeceasingVertex + newDistanceToVertex) {
                    vertex.addPrecedingNode(previousVertex.vertex);
                    vertices.put(vertex.vertex,vertex);
                    precedingNodes.put(edge.vertex,vertex.vertex);
                    thereAndBackNodes.add(vertex);
                }
            }
        }
    }

    private List<String> findPathFromStartNode(String vertex, Map<String,String> precedingNodes) {
        List<String> pathFromVertex = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push(vertex);
        while(precedingNodes.get(vertex) != null){
            vertex = precedingNodes.get(vertex);
            stack.push(vertex);
        }
        while (!stack.isEmpty()) {
                pathFromVertex.add(stack.pop());
            }

        return pathFromVertex;
    }
    private List<String> isPathTABA(String vertex, Map<String,String> precedingNodes, Set<Node> vertices) {
        List<String> list = new ArrayList<>();
        while(precedingNodes.get(vertex) != null){
            if(vertices.contains(new Node(vertex,0))){
                list.add(vertex);
                return list;
            }
            list.add(vertex);
            vertex = precedingNodes.get(vertex);

        }
        return null;

    }


    /**
     * If the graph contains a "goal vertex of the longest valid path" (as
     * defined by the requirements document), returns it.  Else returns null.
     *
     * @return goal vertex of the longest valid path if one exists, null
     * otherwise.
     */
    @Override
    public String goalVertex() {
        if(validPath2 == null)
            return null;
        return this.validPath2.vertex;
    }

    /**
     * Returns the cost (sum of the edge weights) of the longest valid path if
     * one exists, 0.0 otherwise.
     *
     * @return the cost if the graph contains a longest valid path, 0.0
     * otherwise.
     */
    @Override
    public double goalCost() {
        return this.goalWeight;
    }

    /**
     * If a longest valid path exists, returns a ordered sequence of vertices
     * (beginning with the start vertex, and ending with the goal vertex)
     * representing that path.
     * <p>
     * IMPORTANT: given the existence of (by definition) two longest valid paths,
     * this method returns the List with the LESSER of the two List.hashCode()
     * instances.
     *
     * @return one of the two longest paths, Collections.EMPTY_LIST if the graph
     * doesn't contain a longest valid path.
     */
    @Override
    public List<String> getOneLongestPath() {
        if (!isTABA)
            return Collections.emptyList();

        int hashCode1 = validPath1.path.hashCode();
        int hashCode2 = validPath2.path.hashCode();

        if (hashCode1 < hashCode2)
            return validPath1.path;
        return validPath2.path;

    }

    /**
     * If a longest valid path exists, returns the OTHER ordered sequence of
     * vertices (beginning with the start vertex, and ending with the goal
     * vertex) representing that path.
     * <p>
     * IMPORTANT: given the existence of (by definition) two longest valid paths,
     * this method returns the List with the GREATER of the two List.hashCode()
     * instances.
     *
     * @return the other of the two longest paths, Collections.EMPTY_LIST if the
     * graph doesn't contain a longest valid path.
     */
    @Override
    public List<String> getOtherLongestPath() {
        if (!isTABA)
            return Collections.emptyList();

        int hashCode1 = validPath1.path.hashCode();
        int hashCode2 = validPath2.path.hashCode();

        if (hashCode1 > hashCode2)
            return validPath1.path;
        return validPath2.path;
    }



    /******************************************************************************
     *  Compilation:  javac IndexMinPQ.java
     *  Execution:    java IndexMinPQ
     *  Dependencies: StdOut.java
     *  Copyright © 2000–2019, Robert Sedgewick and Kevin Wayne.
     *  Last updated: Thu Aug 11 09:07:21 EDT 2022
     *  Minimum-oriented indexed PQ implementation using a binary heap.
     *
     ******************************************************************************/


    /**
     *  The {@code IndexMinPQ} class represents an indexed priority queue of generic keys.
     *  It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
     *  operations, along with <em>delete</em> and <em>change-the-key</em>
     *  methods. In order to let the client refer to keys on the priority queue,
     *  an integer between {@code 0} and {@code maxN - 1}
     *  is associated with each key—the client uses this integer to specify
     *  which key to delete or change.
     *  It also supports methods for peeking at the minimum key,
     *  testing if the priority queue is empty, and iterating through
     *  the keys.
     *  <p>
     *  This implementation uses a binary heap along with an array to associate
     *  keys with integers in the given range.
     *  The <em>insert</em>, <em>delete-the-minimum</em>, <em>delete</em>,
     *  <em>change-key</em>, <em>decrease-key</em>, and <em>increase-key</em>
     *  operations take &Theta;(log <em>n</em>) time in the worst case,
     *  where <em>n</em> is the number of elements in the priority queue.
     *  Construction takes time proportional to the specified capacity.
     *  <p>
     *  For additional documentation, see
     *  <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
     *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
     *
     *  @author Robert Sedgewick
     *  @author Kevin Wayne
     *
     *  @param <Key> the generic type of key on this priority queue
     */
    private class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
        private int maxN;        // maximum number of elements on PQ
        private int n;           // number of elements on PQ
        private int[] pq;        // binary heap using 1-based indexing
        private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
        private Key[] keys;      // keys[i] = priority of i

        /**
         * Initializes an empty indexed priority queue with indices between {@code 0}
         * and {@code maxN - 1}.
         * @param  maxN the keys on this priority queue are index from {@code 0}
         *         {@code maxN - 1}
         * @throws IllegalArgumentException if {@code maxN < 0}
         */
        public IndexMinPQ(int maxN) {
            if (maxN < 0) throw new IllegalArgumentException();
            this.maxN = maxN;
            n = 0;
            keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
            pq   = new int[maxN + 1];
            qp   = new int[maxN + 1];                   // make this of length maxN??
            for (int i = 0; i <= maxN; i++)
                qp[i] = -1;
        }

        /**
         * Returns true if this priority queue is empty.
         *
         * @return {@code true} if this priority queue is empty;
         *         {@code false} otherwise
         */
        public boolean isEmpty() {
            return n == 0;
        }

        /**
         * Is {@code i} an index on this priority queue?
         *
         * @param  i an index
         * @return {@code true} if {@code i} is an index on this priority queue;
         *         {@code false} otherwise
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         */
        public boolean contains(int i) {
            validateIndex(i);
            return qp[i] != -1;
        }

        /**
         * Returns the number of keys on this priority queue.
         *
         * @return the number of keys on this priority queue
         */
        public int size() {
            return n;
        }

        /**
         * Associates key with index {@code i}.
         *
         * @param  i an index
         * @param  key the key to associate with index {@code i}
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws IllegalArgumentException if there already is an item associated
         *         with index {@code i}
         */
        public void insert(int i, Key key) {
            validateIndex(i);
            if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
            n++;
            qp[i] = n;
            pq[n] = i;
            keys[i] = key;
            swim(n);
        }

        /**
         * Returns an index associated with a minimum key.
         *
         * @return an index associated with a minimum key
         * @throws NoSuchElementException if this priority queue is empty
         */
        public int minIndex() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            return pq[1];
        }

        /**
         * Returns a minimum key.
         *
         * @return a minimum key
         * @throws NoSuchElementException if this priority queue is empty
         */
        public Key minKey() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            return keys[pq[1]];
        }

        /**
         * Removes a minimum key and returns its associated index.
         * @return an index associated with a minimum key
         * @throws NoSuchElementException if this priority queue is empty
         */
        public int delMin() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            int min = pq[1];
            exch(1, n--);
            sink(1);
            assert min == pq[n+1];
            qp[min] = -1;        // delete
            keys[min] = null;    // to help with garbage collection
            pq[n+1] = -1;        // not needed
            return min;
        }

        /**
         * Returns the key associated with index {@code i}.
         *
         * @param  i the index of the key to return
         * @return the key associated with index {@code i}
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws NoSuchElementException no key is associated with index {@code i}
         */
        public Key keyOf(int i) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            else return keys[i];
        }

        /**
         * Change the key associated with index {@code i} to the specified value.
         *
         * @param  i the index of the key to change
         * @param  key change the key associated with index {@code i} to this key
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws NoSuchElementException no key is associated with index {@code i}
         */
        public void changeKey(int i, Key key) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            keys[i] = key;
            swim(qp[i]);
            sink(qp[i]);
        }

        /**
         * Change the key associated with index {@code i} to the specified value.
         *
         * @param  i the index of the key to change
         * @param  key change the key associated with index {@code i} to this key
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @deprecated Replaced by {@code changeKey(int, Key)}.
         */
        @Deprecated
        public void change(int i, Key key) {
            changeKey(i, key);
        }

        /**
         * Decrease the key associated with index {@code i} to the specified value.
         *
         * @param  i the index of the key to decrease
         * @param  key decrease the key associated with index {@code i} to this key
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws IllegalArgumentException if {@code key >= keyOf(i)}
         * @throws NoSuchElementException no key is associated with index {@code i}
         */
        public void decreaseKey(int i, Key key) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            if (keys[i].compareTo(key) == 0)
                throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
            if (keys[i].compareTo(key) < 0)
                throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
            keys[i] = key;
            swim(qp[i]);
        }

        /**
         * Increase the key associated with index {@code i} to the specified value.
         *
         * @param  i the index of the key to increase
         * @param  key increase the key associated with index {@code i} to this key
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws IllegalArgumentException if {@code key <= keyOf(i)}
         * @throws NoSuchElementException no key is associated with index {@code i}
         */
        public void increaseKey(int i, Key key) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            if (keys[i].compareTo(key) == 0)
                throw new IllegalArgumentException("Calling increaseKey() with a key equal to the key in the priority queue");
            if (keys[i].compareTo(key) > 0)
                throw new IllegalArgumentException("Calling increaseKey() with a key strictly less than the key in the priority queue");
            keys[i] = key;
            sink(qp[i]);
        }

        /**
         * Remove the key associated with index {@code i}.
         *
         * @param  i the index of the key to remove
         * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
         * @throws NoSuchElementException no key is associated with index {@code i}
         */
        public void delete(int i) {
            validateIndex(i);
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            int index = qp[i];
            exch(index, n--);
            swim(index);
            sink(index);
            keys[i] = null;
            qp[i] = -1;
        }

        // throw an IllegalArgumentException if i is an invalid index
        private void validateIndex(int i) {
            if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
            if (i >= maxN) throw new IllegalArgumentException("index >= capacity: " + i);
        }

        /***************************************************************************
         * General helper functions.
         ***************************************************************************/
        private boolean greater(int i, int j) {
            return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
        }

        private void exch(int i, int j) {
            int swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }


        /***************************************************************************
         * Heap helper functions.
         ***************************************************************************/
        private void swim(int k) {
            while (k > 1 && greater(k/2, k)) {
                exch(k, k/2);
                k = k/2;
            }
        }

        private void sink(int k) {
            while (2*k <= n) {
                int j = 2*k;
                if (j < n && greater(j, j+1)) j++;
                if (!greater(k, j)) break;
                exch(k, j);
                k = j;
            }
        }


        /***************************************************************************
         * Iterators.
         ***************************************************************************/

        /**
         * Returns an iterator that iterates over the keys on the
         * priority queue in ascending order.
         * The iterator doesn't implement {@code remove()} since it's optional.
         *
         * @return an iterator that iterates over the keys in ascending order
         */
        public Iterator<Integer> iterator() { return new HeapIterator(); }

        private class HeapIterator implements Iterator<Integer> {
            // create a new pq
            private IndexMinPQ<Key> copy;

            // add all elements to copy of heap
            // takes linear time since already in heap order so no keys move
            public HeapIterator() {
                copy = new IndexMinPQ<Key>(pq.length - 1);
                for (int i = 1; i <= n; i++)
                    copy.insert(pq[i], keys[pq[i]]);
            }

            public boolean hasNext()  { return !copy.isEmpty();                     }
            public void remove()      { throw new UnsupportedOperationException();  }

            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return copy.delMin();
            }
        }
    }

}
