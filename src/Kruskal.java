import java.util.*;

/**
 * Created by rubatharisan on 11/12/2016.
 */

public class Kruskal {

    Graph graph;
    List<Edge> spanningTree = new ArrayList<>();
    DisjointSet set = new DisjointSet();

    Kruskal(Graph myGraph) {
        this.graph = myGraph;

    }

    public void run(){
        Edge[] myEdges = this.graph.getEdges();
        Arrays.sort(myEdges);

        for(Vertex v : this.graph.getVertices()){
            set.makeSet(v);
        }

        for(Edge e : myEdges){
            //System.out.println(e.getVertices()[0].getId() + " <--> " + e.getVertices()[1].getId() + ", cost: " + e.getCost());

            Vertex v = set.find(e.getVertices()[0]);
            Vertex w = set.find(e.getVertices()[1]);

            if(v != w){
                    spanningTree.add(e);
                    set.union(v,w);
            }
        }
        /* for(Edge e : spanningTree){
            System.out.println(e.getVertices()[0].getId() + " <--> " + e.getVertices()[1].getId() + ", cost: ");
        } */

        /* int i = 0;
        while(i != myEdges.length && spanningTree.size() != myGraph.getVertices().length){

            i++;
        } */
    }


    public void showPath(){
        System.out.println("----------------------------------");
        System.out.println("undirected edge --> cost");
        System.out.println("----------------------------------");

        for(Edge e : spanningTree){

            Vertex v = e.getVertices()[0];
            Vertex w = e.getVertices()[1];

            System.out.println("(v"+v.getId() + ",v" + w.getId() + ") -> cost: " + e.getCost());
        }

        System.out.println();

    }


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





    }
}
