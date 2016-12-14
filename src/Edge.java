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

    // Get the source of this edge.
    public int getSource(){
        return this.source;
    }

    // Get the target of this edge.
    public int getTarget(){
        return this.target;
    }

    // Get the cost/weight of this edge
    public int getCost(){
        return this.cost;
    }

    // Get the destination of this edge (as a vertex)
    public Vertex getDestination(){
        return this.targetVertex;
    }

    // Get associated vertices to this undirected edge
    public Vertex[] getVertices(){

        return new Vertex[]{
                this.undirectedEdgeToVertex1,
                this.undirectedEdgeToVertex2
        };

    }

    // Print the debugging data
    public void printData(){
        System.out.println("    Destination vertex: " + this.targetVertex.getId());
        System.out.println("    Cost: " + this.cost);
        System.out.println();
    }

    // Comparator to our edge - based on cost.
    @Override
    public int compareTo(Edge e){
        return Integer.compare(this.getCost(), e.getCost());
    }
}
