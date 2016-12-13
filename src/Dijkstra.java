import java.util.*;

/**
 * Created by rubatharisan on 07/12/2016.
 */
public class Dijkstra {
    Vertex source;
    Graph graph;
    boolean showHeapDebug = false;

    Dijkstra(Vertex s, Graph graph){
        this.source = s;
        this.graph = graph;
    }

    public void run(){
        System.out.println("###");
        System.out.println("# Dijkstra's algorithm");
        System.out.println("##");
        System.out.println();


        /* PriorityQueue<Vertex> q = new PriorityQueue<>(vertices.length, new Comparator<Vertex>(){

            public int compare(Vertex vertex1,
                               Vertex vertex2)
            {
                return vertex1.distance.compareTo(vertex2.distance);
            }

        }); */

        Heap q = new Heap(graph.getVertices().length);
        q.debug = showHeapDebug;


        for(Vertex v : graph.getVertices()){
            if(v == source){
                v.distance = 0;
            }

            q.add(v);
        }

        /* test
        System.out.println("---");
        for (Iterator<Vertex> flavoursIter = q.iterator(); flavoursIter.hasNext();){
            Vertex n = flavoursIter.next();
            System.out.println("Vertex: " + n.getId() + " Cost: " + n.distance);
        }
        System.out.println("----"); */




        while(!q.isEmpty()){
            Vertex v = q.poll();

            //System.out.println("Taking out ID: " + v.getId());
            //System.out.println("Taking element with priority " + v.distance);


            v.visited = true;

            for(Edge e : v.adj){

                Vertex w = e.getDestination();

                if(!w.visited){
                    int cvw = e.getCost();

                    if(v.distance + cvw < w.distance){

                        //System.out.println("Adjacent vertex: " + w.destination.getId() + " has cost: " + w.destination.distance);
                        w.distance = v.distance + cvw;
                        //System.out.println("Adjacent vertex: " + w.destination.getId() + " has updated cost: " + w.destination.distance);
                        w.path = v;

                        q.decreaseKey(w);

                    }

                }
            }

            /* test
            System.out.println("---");
            for (Iterator<Vertex> flavoursIter = q.iterator(); flavoursIter.hasNext();){
                Vertex n = flavoursIter.next();
                System.out.println("Vertex: " + n.getId() + " Cost: " + n.distance);
            }
            System.out.println("----"); */
        }

    }

    void showPath(){
        System.out.println("Source vertex: " + source.getId());
        System.out.println("----------------------------------");
        System.out.println("vertex|distance|path");
        System.out.println("----------------------------------");

        for(Vertex v : graph.getVertices()){


            System.out.print("v" + v.getId() + "    cost: " + v.distance + "    ");


            if(v.path != null) {
                Stack<Integer> order = new Stack<>();
                printPath(v, order);
            }

            if(v.distance == Integer.MAX_VALUE){
                System.out.print("Not possible to reach from " + source.getId() + " to ");
            }

            System.out.print(v.getId());

            /*
            for(Edge w : v.adj){
                System.out.println("    to: " + w.destination.getId());
                System.out.println("    cost: " + w.destination.distance);
            }
            */
            System.out.println();
        }

        System.out.println();

    }

    void printPath(Vertex v, Stack<Integer> order){

        if(v.path != null){
            //System.out.print(v.path.getId() + ", ");
            order.push(v.path.getId());
            printPath(v.path, order);
        }

        if(v.path == null){
            Collections.reverse(order);

            for(int i : order){
                System.out.print(i);
                System.out.print(" -> ");

            }
        }

    }

    void showHeap(boolean showDebug){
        this.showHeapDebug = showDebug;
    }
}
