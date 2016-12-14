import java.util.Stack;
import java.util.Collections;

/**
 * Created by rubatharisan on 07/12/2016.
 */

public class Dijkstra {

    // Which vertex are we starting from?
    private Vertex source;

    // Let's define the graph which contains all the vertices.
    private Graph graph;

    // Should we show the heap data?
    private boolean showHeapData = false;

    // Our custom heap
    private Heap heap;

    // Constructor of our dijkstra algorithm
    Dijkstra(Vertex s, Graph graph){
        this.source = s;
        this.graph = graph;
        this.heap = new Heap(graph.getVertices().length);
    }

    // Run our dikstra algorithm with the defined graph and source
    public void run(){
        System.out.println("###");
        System.out.println("# Dijkstra's algorithm");
        System.out.println("##");
        System.out.println();

        // Loop through all of our vertices in the graph.
        for(Vertex v : graph.getVertices()){

            // Set the source vertex as having the distance of 0.
            if(v == source){
                v.setDistance(0);
            }

            // For every vertex in our graph add it to the heap.
            heap.add(v);

        }

        // Show heap?
        this.heap.debug = showHeapData;

        // While there is something in the heap...
        while(!heap.isEmpty()){

            // Poll the vertex with the minimum distance out of the heap.
            Vertex v = heap.poll();

            // Set the vertex as visited
            // v.isVisited = true
            v.setAsVisited();

            // For every edge this vertex has
            for(Edge e : v.getAdjacentVertices()){

                // Get an adjacent vertex
                Vertex w = e.getDestination();

                // Check if the adjacent vertex is visited
                if(!w.isVisited()){

                    // Get the weight / cost of the path to the vertex.
                    int cvw = e.getCost();

                    // If vertex v distance + the cost of getting to the vertex w is cheaper then the current defined
                    // vertex w distance, then set a new distance to vertex w.
                    if(v.getDistance() + cvw < w.getDistance()){

                        // Set the new distance to vertex w.
                        w.setDistance(v.getDistance() + cvw);

                        // Set vertex v as the parent of vertex w.
                        w.setParent(v);

                        // Let's decrease the distance value already previously defined for vertex w.
                        heap.decreaseKey(w);

                    }

                }
            }

        }

    }

    // Do you want to see the heap data?
    public void showHeapData(boolean state){
        this.showHeapData = state;
    }

    // Show path from source vertex to any vertex in the graph
    void showPath(){
        System.out.println("Source vertex: " + source.getId());
        System.out.println("----------------------------------");
        System.out.println("vertex|distance|path");
        System.out.println("----------------------------------");

        // For every vertex in the graph
        for(Vertex v : graph.getVertices()){

            // Print out the vertex id and cost of getting there
            System.out.print("v" + v.getId() + "    cost: " + v.getDistance() + "    ");

            // If the vertex has a parent, then print out the order and path.
            if(v.getParent() != null) {
                Stack<Integer> order = new Stack<>();
                printPath(v, order);
            }

            // If the distance is infinity (Integer.MAX_VALUE) then the source vertex, or it has no way to get to
            // the vertex
            if(v.getDistance() == Integer.MAX_VALUE){
                System.out.print("Not possible to reach from " + source.getId() + " to ");
            }

            // Print out which vertex we are talking about
            System.out.print(v.getId());

            System.out.println();
        }

        System.out.println();

    }

    // Print path (recursively - and add it to our stack)
    void printPath(Vertex v, Stack<Integer> order){

        if(v.getParent() != null){
            order.push(v.getParent().getId());
            printPath(v.getParent(), order);
        }

        // If vertex parent is null, then time to print out our order stack
        if(v.getParent() == null){
            // Let's reverse the order stack first
            Collections.reverse(order);

            for(int i : order){
                System.out.print(i);
                System.out.print(" -> ");

            }
        }

    }

}
