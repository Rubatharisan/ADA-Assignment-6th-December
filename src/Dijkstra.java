import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by rubatharisan on 07/12/2016.
 */
public class Dijkstra {
    Vertex source;
    Vertex[] vertices;

    Dijkstra(Vertex s, Vertex[] vertices){
        this.source = s;
        this.vertices = vertices;
    }

    public void run(){
        System.out.println("###");
        System.out.println("# Dijkstra's algorithm");
        System.out.println("##");
        System.out.println();


        PriorityQueue<Vertex> q = new PriorityQueue<>(vertices.length, new Comparator<Vertex>(){

            public int compare(Vertex vertex1,
                               Vertex vertex2)
            {
                return vertex1.distance.compareTo(vertex2.distance);
            }

        });


        source.distance = 0;
        q.add(source);

        // test
        System.out.println("---");
        for (Iterator<Vertex> flavoursIter = q.iterator(); flavoursIter.hasNext();){
            Vertex n = flavoursIter.next();
            System.out.println("Vertex: " + n.getId() + " Cost: " + n.distance);
        }
        System.out.println("----");




        while(!q.isEmpty()){
            Vertex v = q.poll();

            System.out.println("Taking out ID: " + v.getId());
            System.out.println("Taking element with priority " + v.distance);


            v.visited = true;

            for(Edge w : v.adj){

                if(!w.destination.visited){
                    int cvw = w.cost;

                    if(v.distance + cvw < w.destination.distance){


                        System.out.println("Adjacent vertex: " + w.destination.getId() + " has cost: " + w.destination.distance);
                        w.destination.distance = v.distance + cvw;
                        System.out.println("Adjacent vertex: " + w.destination.getId() + " has updated cost: " + w.destination.distance);
                        w.destination.path = v;

                        if(!q.contains(w.destination)){
                            q.add(w.destination);
                        }
                    }

                }
            }

            // test
            System.out.println("---");
            for (Iterator<Vertex> flavoursIter = q.iterator(); flavoursIter.hasNext();){
                Vertex n = flavoursIter.next();
                System.out.println("Vertex: " + n.getId() + " Cost: " + n.distance);
            }
            System.out.println("----");
        }

        for(Vertex v : vertices){
            System.out.println("Vertex: " + v.getId());
            System.out.println("Distance: " + v.distance);
            if(v.path != null) {
                System.out.println("Path: " + v.path.getId());
            }
            System.out.println();
            /*
            for(Edge w : v.adj){
                System.out.println("    to: " + w.destination.getId());
                System.out.println("    cost: " + w.destination.distance);
            }
            */
        }

    }
}
