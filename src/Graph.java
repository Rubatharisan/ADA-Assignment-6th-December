import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

/**
 * Created by rubatharisan on 07/12/2016.
 */

public class Graph {
    // Our vertices in our graph
    private Vertex[] vertices;

    // Our edges in our graph
    private Edge[] edges;

    // If our graph is directed - use this constructor
    Graph(Vertex[] vertices, Edge[] edges){
        this(vertices, edges, false);
    }

    // If our graph is undirected - use this constructor
    Graph(Vertex[] vertices, Edge[] edges, boolean undirected){

        // Define a HashMap which associates a Vertex ID with a Vertex
        HashMap<Integer, Vertex> myVertices = new HashMap<>();

        // Let's instantiate this.edges with the size of edges
        this.edges = new Edge[edges.length];

        // For every vertex insert it into our HashMap with it's id as key.
        for(Vertex v : vertices){
            myVertices.put(v.getId(), v);
        }

        // Let's count how many edges we have
        int edgeCount = 0;
        for(Edge edge : edges){

            // Let's get the source and target from our hashmap
            Vertex source = myVertices.get(edge.getSource());
            Vertex target = myVertices.get(edge.getTarget());

            // Let's create a new Edge and insert into this graphs edges array
            this.edges[edgeCount] = new Edge(source, target, edge.getCost());

            // Let's add the target to our source vertexs adjacency list
            source.addAdjacentVertex(new Edge(target, edge.getCost()));

            // If this graph is undirected, then let's add it to our targets adjacency list too.
            if(undirected){
                target.addAdjacentVertex(new Edge(source, edge.getCost()));
            }

            edgeCount++;
        }

        // Let's change our hashmap into a array
        this.vertices = new Vertex[myVertices.size()];

        int counter = 0;
        for(Object i : myVertices.keySet().toArray()){
            this.vertices[counter] = myVertices.get(i);
            counter++;
        }

    }

    // Let's print out all adjacency vertexes for every vertex
    public void printAdjacencyList(){
        System.out.println("###");
        System.out.println("# Adjacency list representation of our graph (vertex | adjacent vertex, ...)");
        System.out.println("##");
        System.out.println();


        for(Vertex v : vertices){
            System.out.print(v.getId() + " | ");
            if(!v.getAdjacentVertices().isEmpty()) {
                int counter = 0;

                for(Edge e : v.getAdjacentVertices()){
                    System.out.print(e.getDestination().getId());

                    counter++;
                    if(!(counter == v.getAdjacentVertices().size())){
                        System.out.print(", ");
                    }

                }

            } else {
                System.out.print("(empty)");
            }
            System.out.println();
        }

        System.out.println();
    }

    // Let's print all weight for every adjacent vertex.
    public void printWeightRepresentation(){
        System.out.println("###");
        System.out.println("# Weight representation of our graph (vextex | [ adjacent vertex -> cost ], ...)");
        System.out.println("##");
        System.out.println();


        for(Vertex v : vertices){
            System.out.print(v.getId() + " | ");
            if(!v.getAdjacentVertices().isEmpty()) {
                int counter = 0;

                for(Edge e : v.getAdjacentVertices()){
                    System.out.print("[ " + e.getDestination().getId() + " -> cost: " + e.getCost() + " ]");

                    counter++;
                    if(!(counter == v.getAdjacentVertices().size())){
                        System.out.print(", ");
                    }

                }

            } else {
                System.out.print("(empty)");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Let's print the topological sort for our graph (only works if it is Directed Acyclic Graph - DAG)
    public void printToplogicalSort(){
        System.out.println("###");
        System.out.println("# Topological sorting of our graph");
        System.out.println("##");
        System.out.println();

        // For every vertex
        for(Vertex v : vertices){

            // For every edge in this vertex
            for(Edge w : v.getAdjacentVertices()){

                // For every target vertex (destination) of every edge from source vertex, increment indegree by 1
                w.getDestination().indegree++;
            }
        }

        // Instantiate a queue
        Queue<Vertex> q = new ArrayDeque();

        // For every vertex
        for(Vertex v : vertices){

            // If vertex has no incoming connections (a indegree of 0)
            if(v.indegree == 0){

                // Add it to the queue
                q.add(v);
            }
        }

        // Let's count
        int counter = 0;

        // While our queue is not empty...
        while(!q.isEmpty()){

            // Poll out a vertex from the queue
            Vertex v = q.poll();

            // Set it's topological number (first vertex will have rank = 1)
            v.setTopNum(++counter);

            // For every adjacent vertex,
            for(Edge w : v.getAdjacentVertices()){

                // Decrement a indegree, and if the indegree equals 0, add it to our queue.
                if(--w.getDestination().indegree == 0){
                    q.add(w.getDestination());
                }
            }
        }

        // Make an array which contains our vertices, in a sorted order.
        Vertex[] topologicalSort = new Vertex[vertices.length];

        // Is our graph a directed acyclic graph?
        boolean isDAG = true;

        // For every vertex in our vertices
        for(Vertex v : vertices){

            // If our topNum is = 0, and topNum - 1 = -1, then this is not a directed acyclic graph.
            if(v.getTopNum()-1 < 0){
                isDAG = false;
            } else {
                // Insert the vertex into our array, with it's position.
                topologicalSort[v.getTopNum() - 1] = v;
            }
        }

        // Print out our topological sort.
        if(!isDAG){
            System.out.println("The graph is not a directed acyclic graph (DAG)");
        } else {

            for (int i = 0; i < topologicalSort.length; i++) {
                System.out.print("v" + topologicalSort[i].getId());

                if(i != topologicalSort.length-1){
                    System.out.print(", ");
                }
            }

        }

        System.out.println();
        System.out.println();

    }

    // Get a vertex by it's ID.
    public Vertex getVertexById(int id){
        for(Vertex v : vertices){
            if(v.getId() == id){
                return v;
            }
        }

        return null;
    }

    // Get all vertices
    public Vertex[] getVertices(){
        return this.vertices;
    }

    // Get all edges
    public Edge[] getEdges(){
        return this.edges;
    }


}
