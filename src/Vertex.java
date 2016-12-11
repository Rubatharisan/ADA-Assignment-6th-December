import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Vertex {
    public List<Edge> adj;
    public boolean visited;

    public Integer distance;
    public Vertex path;

    public int indegree = 0;
    public int topNum;

    private int vertexId;

    Vertex(int id){
        this.vertexId = id;
        this.adj = new ArrayList<>();
        this.distance = Integer.MAX_VALUE;
        this.visited = false;
    }

    public int getId(){
        return this.vertexId;
    }

    public void addAdjacentVertex(Edge e){
        this.adj.add(e);
    }


    public void getAdjacentVertices(){
        System.out.println("Vertex: " + this.getId());
        for (Edge e : adj) {
           e.printData();
        }
    }

}
