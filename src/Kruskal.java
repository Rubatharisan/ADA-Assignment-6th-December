import java.util.*;

/**
 * Created by rubatharisan on 11/12/2016.
 */

public class Kruskal {

    private Vertex[] disjointSet;

    Kruskal(Graph myGraph){
        Edge[] myEdges = myGraph.getEdges();

        Arrays.sort(myEdges);

        List<Edge> spanningTree = new ArrayList<>();

        for(Edge e : myEdges){
            System.out.println(e.getVertices()[0].getId() + " <--> " + e.getVertices()[1].getId() + ", cost: " + e.getCost());
        }

        int i = 0;
        while(i != myEdges.length && spanningTree.size() != myGraph.getVertices().length){
            
            i++;
        }
    }


    public static void main(String args[]){
        System.out.println("Hi!");

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






    }
}
