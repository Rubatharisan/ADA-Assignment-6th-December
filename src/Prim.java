import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by rubatharisan on 10/12/2016.
 */
public class Prim {
    private Graph graph;
    private Vertex source;

    Prim(Graph graph){
        this.graph = graph;
    }

    public void run(Vertex s){
        System.out.println("###");
        System.out.println("# Prims algorithm");
        System.out.println("##");
        System.out.println();


        Heap heap = new Heap(graph.getVertices().length);

        heap.debug = false;

        if(s == null){
            int length = graph.getVertices().length;
            s = graph.getVertexById((int) (Math.random() * (length + 1)));
        }

        s.distance = 0;
        this.source = s;

        heap.add(this.source);

        while(!heap.isEmpty()){
            Vertex v = heap.poll();

            v.visited = true;
            System.out.println("I am at vertex: " + v.getId());

            for(Edge w : v.adj){
               // Edge w = v.adj.get(i);
                if(!w.destination.visited) {
                    int cvw = w.cost;
                    System.out.print("Vertex: " + w.destination.getId() + ", cost: " + w.cost);

                    if(w.destination.distance > cvw){
                        w.destination.path = v;
                        w.destination.distance = cvw;

                        if(!heap.contains(w.destination)){
                            System.out.println(" ... adding to heap");
                            heap.add(w.destination);

                        } else {
                            System.out.println(" ... updating existing heap element");
                            heap.decreaseKey(w.destination);
                        }

                    } else {
                        System.out.println(" ... not updating existing heap element");
                    }

                }

            }

            System.out.println();
        }

    }

    void showPath(){
        System.out.println("Source vertex: " + source.getId());
        System.out.println("----------------------------------");
        System.out.println("vertex|distance|edge");
        System.out.println("----------------------------------");

        for(Vertex v : graph.getVertices()){


            System.out.print("v" + v.getId() + "    cost: " + v.distance + "    ");


            if(v.path != null) {
                System.out.print("(v" + v.getId() + ",v" + v.path.getId() + ")");
            }

            if(v.distance == Integer.MAX_VALUE){
                System.out.print("Not possible to reach from " + source.getId() + " to ");
            }


            System.out.println();
        }

        System.out.println();

    }

    /* public static void main(String args[]){
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

        Prim prim = new Prim(myGraph3);
        prim.run(myGraph3.getVertexById(1));
        prim.showPath();
    }
    */
}
