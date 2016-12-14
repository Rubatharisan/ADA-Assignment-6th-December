import java.util.*;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Tester {

    public static void main(String args[]){

        // Define vertices, edges and graph
        Vertex[] myVertices;
        Edge[] myEdges;
        Graph myGraph;

        // Define dijkstra, prim and kruskal
        Dijkstra dijkstra;
        Prim prim;
        Kruskal kruskal;

        /*
            Example 1, Figure 9.1 from the book.
         */

        System.out.println();
        System.out.println("******************************************************");
        System.out.println("The following graph is taken from the book, figure: 9.1");
        System.out.println("We will be using Dijkstra");
        System.out.println("******************************************************");
        System.out.println();

        // new Vertex(int id) - define all our vertices from figure: 9.1
        myVertices = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        myEdges = new Edge[]{
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
        myGraph = new Graph(myVertices, myEdges);

        myGraph.printAdjacencyList();
        myGraph.printWeightRepresentation();
        myGraph.printToplogicalSort();

        /* Dijkstra */
        dijkstra = new Dijkstra(myGraph.getVertexById(1), myGraph);

        // Show output of our custom priority queue.
        dijkstra.showHeapData(false);
        dijkstra.run();
        dijkstra.showPath();


        /*
            Example 2, Figure 9.8 from the book.
         */

        System.out.println();
        System.out.println("******************************************************");
        System.out.println("Following graph is taken from the book, figure: 9.8");
        System.out.println("We will be using Dijkstra");
        System.out.println("******************************************************");
        System.out.println();


        // new Vertex(int id)
        myVertices = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        myEdges = new Edge[]{
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

        myGraph = new Graph(myVertices, myEdges);
        myGraph.printAdjacencyList();
        myGraph.printWeightRepresentation();
        myGraph.printToplogicalSort();

        /* Dijkstra */

        dijkstra = new Dijkstra(myGraph.getVertexById(1), myGraph);
        dijkstra.showHeapData(false);
        dijkstra.run();
        dijkstra.showPath();



        /*
            Example 3, Figure 9.50 from the book.
         */

        System.out.println();
        System.out.println("******************************************************");
        System.out.println("Following graph is taken from the book, figure: 9.50");
        System.out.println("We will be using Dijkstra, Prim and Kruskal");
        System.out.println("******************************************************");
        System.out.println();

        // new Vertex(int id)
        myVertices = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        myEdges = new Edge[]{
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

        myGraph = new Graph(myVertices, myEdges, true);
        myGraph.printAdjacencyList();
        myGraph.printWeightRepresentation();
        myGraph.printToplogicalSort();

        dijkstra = new Dijkstra(myGraph.getVertexById(1), myGraph);

        // Show output of our custom priority queue.
        dijkstra.showHeapData(false);
        dijkstra.run();
        dijkstra.showPath();

        // new Vertex(int id)
        myVertices = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        myEdges = new Edge[]{
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

        // Resetting our graph
        myGraph = new Graph(myVertices, myEdges, true);

        prim = new Prim(myGraph, myGraph.getVertexById(1));
        prim.run();
        prim.showHeapData(false);
        prim.showPath();


        // new Vertex(int id)
        myVertices = new Vertex[]{
                new Vertex(1),
                new Vertex(2),
                new Vertex(3),
                new Vertex(4),
                new Vertex(5),
                new Vertex(6),
                new Vertex(7)
        };

        // new Edge(int source, int target, int cost)
        myEdges = new Edge[]{
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

        // Resetting our graph
        myGraph = new Graph(myVertices, myEdges, true);

        kruskal = new Kruskal(myGraph);
        kruskal.run();
        kruskal.showPath();

    }


}

