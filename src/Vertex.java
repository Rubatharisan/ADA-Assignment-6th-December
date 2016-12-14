import java.util.ArrayList;
import java.util.List;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Vertex {
    /* A list containing adjacent vertices, if a adjacent vertex does not have
    * this vertex in their adj list, then it can not come back to it self. (Directed)
    * Used in: Dijkstra, Prim.
    * */
    private List<Edge> adj;

    // Is this vertex visited? - used in: Dijkstra, Prim.
    private boolean isVisited;

    // How to get to this vertex? which parent should it descend from? - used in: Dijkstra, Prim.
    private Vertex parent;

    // How far is there between this vertex, and it's parent? - used in: Dijkstra, Prim.
    private Integer distance;

    // How many incoming connections to this vertex? - used in: topological sort, graph.
    public int indegree = 0;

    // Which topological order is this vertex in? - used in: topological sort, graph.
    private int topNum;

    // What is the ID of this vertex?
    private int vertexId;

    // Constructor of this vertex, needs a ID.
    Vertex(int id){
        // Set the id
        this.vertexId = id;

        // Define a new arraylist for our adjacent vertices
        this.adj = new ArrayList<>();

        // Set the distance to the parent as infinity (practical solution: Integer.MAX_VALUE)
        this.distance = Integer.MAX_VALUE;

        // Set this vertex as unvisited
        this.isVisited = false;
    }

    // Return this vertex id.
    public int getId(){
        return this.vertexId;
    }

    // Add a adjacent vertex to our adj list
    public void addAdjacentVertex(Edge e){
        this.adj.add(e);
    }

    // Get all adjacent vertices to this vertex.
    public List<Edge> getAdjacentVertices(){
        return this.adj;
    }

    // Print all adjacent vertices to this vertex
    public void printAdjacentVertices(){
        System.out.println("Vertex: " + this.getId());
        for (Edge e : adj) {
           e.printData();
        }
    }

    // Is this vertex visited?
    public boolean isVisited(){
        return this.isVisited;
    }

    // Set this vertex as visited
    public void setAsVisited(){
        this.isVisited = true;
    }

    // Who is this vertex's parent?
    public Vertex getParent(){
        return this.parent;
    }

    // Set the parent of this vertex.
    public void setParent(Vertex parent){
        this.parent = parent;
    }

    // How far is there from parent to this vertex?
    public Integer getDistance(){
        return this.distance;
    }

    // Set the distance between this vertex and it's parent.
    public void setDistance(Integer distance){
        this.distance = distance;
    }

    // Set the topological number of this vertex.
    public void setTopNum(int position){
        this.topNum = position;
    }

    // Get the topological number of this vertex.
    public int getTopNum(){
        return this.topNum;
    }

}
