/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Edge {
    private int source;
    private int target;

    public Vertex destination;
    public int cost;

    public Edge(int source, int target, int cost){
        this.source = source;
        this.target = target;
        this.cost = cost;
    }

    public Edge(Vertex destination, int cost){
        this.destination = destination;
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

    public void printData(){
        System.out.println("    Destination vertex: " + this.destination.getId());
        System.out.println("    Cost: " + this.cost);
        System.out.println();
    }
}
