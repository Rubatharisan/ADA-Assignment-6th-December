import java.util.List;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Vertex {
    public List adj;
    public boolean visited;
    public int distance;
    public Vertex path;

    private int vertexId;

    Vertex(int id){
        this.vertexId = id;
    }

    public int getId(){
        return this.vertexId;
    }

}
