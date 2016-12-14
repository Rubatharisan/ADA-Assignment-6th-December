
/**
 * Created by rubatharisan on 13/12/2016.
 */

public class DisjointSet {

    // v.path = v.parent
    // Create a set and set the vertex as the parent of it
    public void makeSet(Vertex v) {
        v.setParent(v);
    }

    // Find a parent to the inputted vertex
    public Vertex find(Vertex v){
        //System.out.println("Vertex " + v.getId() + ", path: " + v.parent.getId());

        if(v.getParent() == v){
            return v;
        }

        return find(v.getParent());

    }

    // Union vertex v and w, so they share the same parent.
    public void union(Vertex v, Vertex w){
        Vertex vRoot = find(v);
        Vertex wRoot = find(w);

        vRoot.setParent(wRoot);
    }

}
