/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Edge {
    public Vertex destination;
    public int cost;

    public Edge(Vertex d, int cost){
        this.destination = d;
        this.cost = cost;
    }

    public void printData(){
        System.out.println("    Destination vertex: " + this.destination.getId());
        System.out.println("    Cost: " + this.cost);
        System.out.println();
    }
}
