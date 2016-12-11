import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

/**
 * Created by rubatharisan on 07/12/2016.
 */
public class Graph {
    private Vertex[] vertices;

    Graph(Vertex[] vertices, Edge[] edges){
        this(vertices, edges, false);
    }

    Graph(Vertex[] vertices, Edge[] edges, boolean bidirectional){

        HashMap<Integer, Vertex> myVertices = new HashMap<>();

        for(Vertex v : vertices){
            myVertices.put(v.getId(), v);
        }

        for(Edge edge : edges){
            Vertex source = myVertices.get(edge.getSource());
            Vertex target = myVertices.get(edge.getTarget());
            source.addAdjacentVertex(new Edge(target, edge.getCost()));

            if(bidirectional){
                target.addAdjacentVertex(new Edge(source, edge.getCost()));
            }

        }


        Vertex[] theVertices = new Vertex[myVertices.size()];

        int counter = 0;
        for(Object i : myVertices.keySet().toArray()){
            theVertices[counter] = myVertices.get(i);
            counter++;
        }

        this.vertices = theVertices;


    }

    public void printAdjacencyList(){
        System.out.println("###");
        System.out.println("# Adjacency list representation of our graph (vertex | adjacent vertex, ...)");
        System.out.println("##");
        System.out.println();


        for(Vertex v : vertices){
            System.out.print(v.getId() + " | ");
            if(!v.adj.isEmpty()) {
                int counter = 0;

                for(Edge e : v.adj){
                    System.out.print(e.destination.getId());

                    counter++;
                    if(!(counter == v.adj.size())){
                        System.out.print(", ");
                    }

                }

            } else {
                System.out.print("(empty)");
            }
            System.out.println();
        }

        System.out.println();
    }

    public void printWeightRepresentation(){
        System.out.println("###");
        System.out.println("# Weight representation of our graph (vextex | [ adjacent vertex -> cost ], ...)");
        System.out.println("##");
        System.out.println();


        for(Vertex v : vertices){
            System.out.print(v.getId() + " | ");
            if(!v.adj.isEmpty()) {
                int counter = 0;

                for(Edge e : v.adj){
                    System.out.print("[ " + e.destination.getId() + " -> cost: " + e.cost + " ]");

                    counter++;
                    if(!(counter == v.adj.size())){
                        System.out.print(", ");
                    }

                }

            } else {
                System.out.print("(empty)");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printToplogicalSort(){
        System.out.println("###");
        System.out.println("# Topological sorting of our graph");
        System.out.println("##");
        System.out.println();


        for(Vertex v : vertices){
            for(Edge w : v.adj){
                w.destination.indegree++;
            }
        }

        Queue<Vertex> q = new ArrayDeque();


        for(Vertex v : vertices){
            if(v.indegree == 0){
                q.add(v);
            }
        }


        int counter = 0;

        while(!q.isEmpty()){
            Vertex v = q.poll();
            v.topNum = ++counter;

            for(Edge w : v.adj){
                if(--w.destination.indegree == 0){
                    q.add(w.destination);
                }
            }
        }

        Vertex[] topologicalSort = new Vertex[vertices.length];
        boolean isDAG = true;

        for(Vertex v : vertices){
            if(v.topNum-1 < 0){
                isDAG = false;
            } else {
                topologicalSort[v.topNum - 1] = v;
            }
        }

        if(!isDAG){
            System.out.println("The graph is not a directed acyclic graph (DAG)");
        } else {

            for (int i = 0; i < topologicalSort.length; i++) {
                System.out.print("v" + topologicalSort[i].getId());

                if(i != topologicalSort.length-1){
                    System.out.print(", ");
                }
            }

        }

        System.out.println();
        System.out.println();

    }

    public Vertex getVertexById(int id){
        for(Vertex v : vertices){
            if(v.getId() == id){
                return v;
            }
        }

        return null;
    }

    public Vertex[] getVertices(){
        return this.vertices;
    }


}
