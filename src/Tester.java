import java.util.*;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Tester {

    public static void main(String args[]){

        /*
            Example 1, Figure 9.1 from the book.
         */

        System.out.println();
        System.out.println("*********");
        System.out.println("Following graph is taken from the book, figure: 9.1");
        System.out.println("*********");
        System.out.println();

        // new Vertex(int id)
        Vertex[] myVertices = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        Edge[] myEdges = new Edge[]{
            new Edge(1,2,1),
            new Edge(1,3,1),
            new Edge(1,4,1),
            new Edge(2,4,1),
            new Edge(2,5,1),
            new Edge(3,6,1),
            new Edge(4,3,1),
            new Edge(4,6,1),
            new Edge(4,7,1),
            new Edge(5,4,1),
            new Edge(5,7,1),
            new Edge(7,6,1)
        };

        // new Graph(Vertex[] myVertices, Edge[] myEdges)
        Graph myGraph = new Graph(myVertices, myEdges);
        myGraph.printAdjacencyList();
        myGraph.printWeightRepresentation();
        myGraph.printToplogicalSort();

        Dijkstra dijkstra = new Dijkstra(myGraph.getVertexById(1), myGraph.getGraph());
        dijkstra.run();
        dijkstra.showPath();


        /*
            Example 2, Figure 9.8 from the book.
         */

        System.out.println();
        System.out.println("*********");
        System.out.println("Following graph is taken from the book, figure: 9.8");
        System.out.println("*********");
        System.out.println();


        // new Vertex(int id)
        Vertex[] myVertices2 = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        Edge[] myEdges2 = new Edge[]{
                new Edge(1,4,1),
                new Edge(1,2,2),
                new Edge(2,4,3),
                new Edge(2,5,10),
                new Edge(3,1,4),
                new Edge(3,6,5),
                new Edge(4,3,2),
                new Edge(4,5,2),
                new Edge(4,6,8),
                new Edge(4,7,4),
                new Edge(5,7,6),
                new Edge(7,6,1)
        };

        Graph myGraph2 = new Graph(myVertices2, myEdges2);
        myGraph2.printAdjacencyList();
        myGraph2.printWeightRepresentation();
        myGraph2.printToplogicalSort();

        Dijkstra dijkstra2 = new Dijkstra(myGraph2.getVertexById(6), myGraph2.getGraph());
        dijkstra2.run();
        dijkstra2.showPath();


    }


}

