import java.util.ArrayList;
import java.util.List;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Vertex {
    public List<Edge> adj;
    public boolean visited;
    public int distance;
    public Vertex path;
    public int indegree;
    public int topNum;

    private int vertexId;

    Vertex(int id){
        this.vertexId = id;
        this.adj = new ArrayList<>();
    }

    public int getId(){
        return this.vertexId;
    }

    public void addAdjecentVertex(Edge e){
        this.adj.add(e);
    }


    public void getAdjecentVertexes(){
        System.out.println("Vertex: " + this.getId());
        for (Edge e : adj) {
           e.printData();
        }

    }

}
