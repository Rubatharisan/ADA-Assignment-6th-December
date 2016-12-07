import java.util.*;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Tester {

    public static void main(String args[]){

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
            new Edge(1, 2, 2),
            new Edge(1, 4, 1)
        };

        // new Graph(Vertex[] myVertices, Edge[] myEdges)
        Graph myGraph = new Graph(myVertices, myEdges);
        myGraph.printAdjacencyList();
        myGraph.printWeightRepresentation();
        myGraph.printToplogicalSort();

        Dijkstra dijkstra = new Dijkstra(myGraph.getVertexById(1), myGraph.getGraph());
        dijkstra.run();
    }


}

