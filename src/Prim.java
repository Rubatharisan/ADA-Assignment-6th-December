import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by rubatharisan on 10/12/2016.
 */
public class Prim {
    // Define our graph
    private Graph graph;

    // Define our source
    private Vertex source;

    // Define our heap
    private Heap heap;
    private boolean showHeapData = false;


    // Construct our prim
    Prim(Graph graph, Vertex s){
        this.graph = graph;
        this.source = s;
        this.heap = new Heap(graph.getVertices().length);
    }

    // Run our prim algorithm
    public void run(){
        System.out.println("###");
        System.out.println("# Prims algorithm");
        System.out.println("##");
        System.out.println();

        // Is a source set?
        if(this.source == null){
            int length = graph.getVertices().length;
            this.source = graph.getVertexById((int) (Math.random() * (length + 1)));
        }

        // Set the distance of the source to 0
        this.source.setDistance(0);

        // Add our source to the heap
        heap.add(this.source);

        // Should we see debugging data?
        this.heap.debug = showHeapData;

        // While our heap is not empty...
        while(!heap.isEmpty()){

            // Poll the vertex with the minimum distance out of the heap.
            Vertex v = heap.poll();

            // Set the vertex as visited
            // v.isVisited = true
            v.setAsVisited();

            //System.out.println("I am at vertex: " + v.getId());

            // For every edge this vertex has
            for(Edge e : v.getAdjacentVertices()){

                // Get an adjacent vertex
                Vertex w = e.getDestination();

                // Check if the adjacent vertex is visited
                if(!w.isVisited()) {

                    // Get the weight / cost of the path to the vertex.
                    int cvw = e.getCost();
                    System.out.print("Vertex: " + w.getId() + ", cost: " + e.getCost() + ", from: " + v.getId());

                    // Check if the distance is bigger than the cost
                    if(w.getDistance() > cvw){

                        // If it is, then set the parent as vertex v
                        w.setParent(v);

                        // Set the distance of the cost
                        w.setDistance(cvw);

                        // We need to put our finding into our heap.
                        // We add the adjacent vertex w to the heap, if existing then we just decrease the key of w.
                        if(!heap.contains(w)){

                            System.out.println(" ... adding to heap");
                            heap.add(w);

                        } else {
                            System.out.println(" ... updating existing heap element");
                            heap.decreaseKey(w);
                        }

                    } else {
                        System.out.println(" ... not updating existing heap element");
                    }

                }

            }

        }
        System.out.println();

    }

    // Show the spanning tree.
    void showPath(){
        System.out.println("-- Spanning tree -- Prim");
        System.out.println("Source vertex: " + source.getId() + ", which we span from");
        System.out.println("----------------------------------");
        System.out.println("undirected edge = cost");
        System.out.println("----------------------------------");

        for(Vertex v : graph.getVertices()){

            if(v.getId() != source.getId()) {

                System.out.println("(v"+v.getId() + ",v" + v.getParent().getId() + ") = cost: " + v.getDistance());


                /*

                System.out.print("v" + v.getId() + "    cost: " + v.getDistance() + "    ");



                if (v.getParent() != null) {
                    System.out.print("(v" + v.getId() + ",v" + v.getParent().getId() + ")");
                }

                if (v.getDistance() == Integer.MAX_VALUE) {
                    System.out.print("Not possible to reach from " + source.getId() + " to ");
                }

                */

            }

        }

        System.out.println();

    }

    public void showHeapData(boolean state){
        this.showHeapData = state;
    }

    /*
    public static void main(String args[]){
        Vertex[] myVertices3 = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        Edge[] myEdges3 = new Edge[]{
                new Edge(1,2,2),
                new Edge(1,3,4),
                new Edge(1,4,1),
                new Edge(2,4,3),
                new Edge(2,5,10),
                new Edge(3,4,2),
                new Edge(3,6,5),
                new Edge(4,5,7),
                new Edge(4,7,4),
                new Edge(4,6,8),
                new Edge(5,7,6),
                new Edge(6,7,1)
        };

        Graph myGraph3 = new Graph(myVertices3, myEdges3, true);

        Prim prim = new Prim(myGraph3, myGraph3.getVertexById(1));
        prim.run();
        prim.showPath();
    }
    */

}
