/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Edge implements Comparable<Edge>{

    // To be used when easy-defining a edge.
    private int source;
    private int target;

    // To be used as a directed vertex
    private Vertex targetVertex;

    // To be used as a undirected edge
    private Vertex undirectedEdgeToVertex1;
    private Vertex undirectedEdgeToVertex2;

    // Cost of using the edge
    private int cost;

    // Input, int source vertex -> int target vertex.
    public Edge(int source, int target, int cost){
        this.source = source;
        this.target = target;
        this.cost = cost;
    }

    // if a directed graph
    public Edge(Vertex destination, int cost){
        this.targetVertex = destination;
        this.cost = cost;
    }

    // if a undirected graph
    public Edge(Vertex undirectedVertex1, Vertex undirectedVertex2, int cost){
        this.undirectedEdgeToVertex1 = undirectedVertex1;
        this.undirectedEdgeToVertex2 = undirectedVertex2;
        this.cost = cost;
    }

    public int getSource(){
        return this.source;
    }

    public int getTarget(){
        return this.target;
    }

    public int getCost(){
        return this.cost;
    }

    public Vertex getDestination(){
        return this.targetVertex;
    }

    public Vertex[] getVertices(){

        return new Vertex[]{
                this.undirectedEdgeToVertex1,
                this.undirectedEdgeToVertex2
        };

    }

    public void printData(){
        System.out.println("    Destination vertex: " + this.targetVertex.getId());
        System.out.println("    Cost: " + this.cost);
        System.out.println();
    }

    @Override
    public int compareTo(Edge e){
        return Integer.compare(this.getCost(), e.getCost());
    }
}
