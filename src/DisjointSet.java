/**
 * Created by rubatharisan on 13/12/2016.
 */
public class DisjointSet {

    // v.path = v.parent
    public void makeSet(Vertex v){
        v.path = v;
    }

    public Vertex find(Vertex v){
        //System.out.println("Vertex " + v.getId() + ", path: " + v.parent.getId());

        if(v.path == v){
            return v;
        }

        return find(v.path);


    }

    public void union(Vertex v, Vertex w){
        Vertex vRoot = find(v);
        Vertex wRoot = find(w);

        vRoot.path = wRoot;
    }



}
