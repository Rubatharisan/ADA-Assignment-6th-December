import java.util.*;

/**
 * Created by rubatharisan on 11/12/2016.
 */

public class Kruskal {

    // Our graph
    private Graph graph;

    // Our spanning tree
    private List<Edge> spanningTree = new ArrayList<>();

    // Our disjoint set
    private DisjointSet set = new DisjointSet();

    // Our constructor of kruskal
    Kruskal(Graph myGraph) {
        this.graph = myGraph;
    }

    // Let's run our kruskal algorithm
    public void run(){

        System.out.println("###");
        System.out.println("# Kruskals algorithm");
        System.out.println("##");
        System.out.println();

        // First get all edges from our graph
        Edge[] myEdges = this.graph.getEdges();

        // Let's sort our edges so the smallest cost comes earliest in the array
        Arrays.sort(myEdges);

        // Then for each vertex in our graph, let's make a set
        for(Vertex v : this.graph.getVertices()){
            set.makeSet(v);
        }

        // For each edge in our sorted array of edges.
        for(Edge e : myEdges){
            //System.out.println(e.getVertices()[0].getId() + " <--> " + e.getVertices()[1].getId() + ", cost: " + e.getCost());

            // Get the parent set of vertex v
            Vertex v = set.find(e.getVertices()[0]);

            // Get the parent set of vertex w
            Vertex w = set.find(e.getVertices()[1]);

            // If the root parent of vertex v and w is not equal to each other.
            if(v != w){

                    // Then add it to our spanning tree
                    spanningTree.add(e);

                    // Apply an union (apply w's root parent as v's parent.)
                    set.union(v,w);

                    // Break out of our for loop, if we got a path to all vertices.
                    if(spanningTree.size() == this.graph.getVertices().length - 1){
                        break;
                    }
            }
        }

    }

    // Show the the combinations
    public void showPath(){
        System.out.println("-- Spanning tree -- Kruskal");
        System.out.println("----------------------------------");
        System.out.println("undirected edge = cost");
        System.out.println("----------------------------------");

        for(Edge e : spanningTree){

            Vertex v = e.getVertices()[0];
            Vertex w = e.getVertices()[1];

            System.out.println("(v"+v.getId() + ",v" + w.getId() + ") = cost: " + e.getCost());
        }

        System.out.println();

    }

    /* // Tester static void main
    public static void main(String args[]){

        Vertex[] myVertices4 = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        Edge[] myEdges4 = new Edge[]{
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


        Graph graph4 = new Graph(myVertices4,myEdges4,true);

        Kruskal kruskal = new Kruskal(graph4);
        kruskal.run();
        kruskal.showPath();


    } */
}
