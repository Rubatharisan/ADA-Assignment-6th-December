import java.util.*;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Tester {
    static List<Vertex> vertices = new ArrayList<>();

    public static void main(String args[]){


        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);


        // Edges for vertex 1
        v1.addAdjecentVertex(new Edge(v2, 2));
        v1.addAdjecentVertex(new Edge(v4, 1));

        // Edges for vertex 2
        v2.addAdjecentVertex(new Edge(v5, 10));
        v2.addAdjecentVertex(new Edge(v4, 3));

        // Edges for vertex 3
        //v3.addAdjecentVertex(new Edge(v1, 4));
        v3.addAdjecentVertex(new Edge(v6, 5));

        // Edges for vertex 4
        v4.addAdjecentVertex(new Edge(v3, 2));
        v4.addAdjecentVertex(new Edge(v5, 2));
        v4.addAdjecentVertex(new Edge(v6, 8));
        v4.addAdjecentVertex(new Edge(v7, 4));

        // Edges for vertex 5
        v5.addAdjecentVertex(new Edge(v7, 6));

        // Edges for vertex 6
        // No edges

        // Edges for vertex 7
        v7.addAdjecentVertex(new Edge(v6, 1));


        /*
        v1.getAdjecentVertexes();
        v2.getAdjecentVertexes();
         */

        // Add vertexes to vertices

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);

        //System.out.println(myVertex.getId());
        dijkstra(v1);
        toplogicalSort();
    }

    static void dijkstra(Vertex s){
        Queue<Vertex> q = new LinkedList<>();

        int INFINITY = Integer.MAX_VALUE;

        for(Vertex v : vertices){
            v.distance = INFINITY;
            v.visited = false;
        }

        s.distance = 0;

        q.add(s);

        for(Edge e : s.adj) {
            q.add(e.destination);
        }

        while(!q.isEmpty()){
            Vertex v = q.poll();

            v.visited = true;

            for(Edge w : v.adj){

                if(!w.destination.visited){
                    int cvw = w.cost;

                    if(v.distance + cvw < w.destination.distance){
                        w.destination.distance = v.distance + cvw;
                        w.destination.path = v;
                    }

                    q.add(w.destination);
                }
            }
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

    static void printPath(Vertex v){
        if( v.path != null ){
            printPath(v.path);
            System.out.println(" to ");
        }

        System.out.println(v.getId());
    }

    static void toplogicalSort(){


        for(Vertex v : vertices){
            v.indegree = 0;
        }

        for(Vertex v : vertices){
            for(Edge w : v.adj){
                w.destination.indegree++;
            }
        }

        Queue<Vertex> q = new ArrayDeque();
        int counter = 0;

        for(Vertex v : vertices){
            if(v.indegree == 0){
                System.out.println(v.getId());

                q.add(v);
                System.out.println(v.getId());
            }
        }

        while(!q.isEmpty()){
            Vertex v = q.poll();
            v.topNum = ++counter;

            for(Edge w : v.adj){
                if(--w.destination.indegree == 0){
                    q.add(w.destination);
                }
            }
        }

        for(Vertex v : vertices){
            System.out.println("Vertex: " + v.getId());
            System.out.println(v.topNum);
        }
    }
}

