import java.util.*;

/**
 * Created by rubatharisan on 05/12/2016.
 */
public class Tester {

    public static void main(String args[]){


        /*
        Example 1
         */
        List<Vertex> vertices = new ArrayList<>();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);


        // Edges for vertex 1
        v1.addAdjacentVertex(new Edge(v2, 2));
        v1.addAdjacentVertex(new Edge(v4, 1));

        // Edges for vertex 2
        v2.addAdjacentVertex(new Edge(v5, 10));
        v2.addAdjacentVertex(new Edge(v4, 3));

        // Edges for vertex 3
        //v3.addAdjacentVertex(new Edge(v1, 4));
        v3.addAdjacentVertex(new Edge(v6, 5));

        // Edges for vertex 4
        v4.addAdjacentVertex(new Edge(v3, 2));
        v4.addAdjacentVertex(new Edge(v5, 2));
        v4.addAdjacentVertex(new Edge(v6, 8));
        v4.addAdjacentVertex(new Edge(v7, 4));

        // Edges for vertex 5
        v5.addAdjacentVertex(new Edge(v7, 6));

        // Edges for vertex 6
        // No edges

        // Edges for vertex 7
        v7.addAdjacentVertex(new Edge(v6, 1));


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

        printGraphAdjacencyList(vertices);
        printWeightRepresentation(vertices);
        toplogicalSort(vertices);
        
        /*
        Example 2
         */

        List<Vertex> vertices2 = new ArrayList<>();
        Vertex vr1 = new Vertex(1);
        Vertex vr2 = new Vertex(2);
        Vertex vr3 = new Vertex(3);
        Vertex vr4 = new Vertex(4);
        Vertex vr5 = new Vertex(5);
        Vertex vr6 = new Vertex(6);
        Vertex vr7 = new Vertex(7);

        // Edges for vertex 1
        vr1.addAdjacentVertex(new Edge(vr3, 1));
        vr1.addAdjacentVertex(new Edge(vr4, 1));
        vr1.addAdjacentVertex(new Edge(vr2, 1));

        // Edges for vertex 2
        vr2.addAdjacentVertex(new Edge(vr5, 1));
        vr2.addAdjacentVertex(new Edge(vr4, 1));

        // Edges for vertex 3
        vr3.addAdjacentVertex(new Edge(vr6, 1));

        // Edges for vertex 4
        vr4.addAdjacentVertex(new Edge(vr3, 1));
        vr4.addAdjacentVertex(new Edge(vr6, 1));
        vr4.addAdjacentVertex(new Edge(vr7, 1));


        // Edges for vertex 5
        vr5.addAdjacentVertex(new Edge(vr7, 1));
        vr5.addAdjacentVertex(new Edge(vr4, 1));

        // Edges for vertex 6
        // No edges

        // Edges for vertex 7
        vr7.addAdjacentVertex(new Edge(vr6, 1));

        // Add vertexes to vertices
        vertices2.add(vr1);
        vertices2.add(vr2);
        vertices2.add(vr3);
        vertices2.add(vr4);
        vertices2.add(vr5);
        vertices2.add(vr6);
        vertices2.add(vr7);

        printGraphAdjacencyList(vertices2);
        printWeightRepresentation(vertices2);
        toplogicalSort(vertices2);

        //dijkstra(v1, vertices);
        //toplogicalSort(vertices);
    }

    static void printGraphAdjacencyList(List<Vertex> vertices){
        System.out.println("###");
        System.out.println("# Adjacency list representation of our graph (vertex | adjacent vertex, ...)");
        System.out.println("##");
        System.out.println();


        for(Vertex v : vertices){
            System.out.print(v.getId() + " | ");
            if(!v.adj.isEmpty()) {
                int counter = 0;

                for(Edge e : v.adj){
                    System.out.print(e.destination.getId());

                    counter++;
                    if(!(counter == v.adj.size())){
                        System.out.print(", ");
                    }

                }

            } else {
                System.out.print("(empty)");
            }
            System.out.println();
        }

        System.out.println();
    }


    static void printWeightRepresentation(List<Vertex> vertices){
        System.out.println("###");
        System.out.println("# Weight representation of our graph (vextex | adjacent vertex -> cost, ...)");
        System.out.println("##");
        System.out.println();


        for(Vertex v : vertices){
            System.out.print(v.getId() + " | ");
            if(!v.adj.isEmpty()) {
                int counter = 0;

                for(Edge e : v.adj){
                    System.out.print("[ " + e.destination.getId() + " -> cost: " + e.cost + " ]");

                    counter++;
                    if(!(counter == v.adj.size())){
                        System.out.print(", ");
                    }

                }

            } else {
                System.out.print("(empty)");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void dijkstra(Vertex s, List<Vertex> vertices){
        System.out.println("###");
        System.out.println("# Dijkstra's algorithm");
        System.out.println("##");
        System.out.println();


        PriorityQueue<Vertex> q = new PriorityQueue<>(vertices.size(), new Comparator<Vertex>(){

            public int compare(Vertex vertex1,
                               Vertex vertex2)
            {
                return vertex1.distance.compareTo(vertex2.distance);
            }

        });


        s.distance = 0;
        q.add(s);

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

    static void toplogicalSort(List<Vertex> vertices){
        System.out.println("###");
        System.out.println("# Topological sorting of our graph");
        System.out.println("##");
        System.out.println();


        for(Vertex v : vertices){
            for(Edge w : v.adj){
                w.destination.indegree++;
            }
        }

        Queue<Vertex> q = new ArrayDeque();


        for(Vertex v : vertices){
            if(v.indegree == 0){
                q.add(v);
            }
        }


        int counter = 0;

        while(!q.isEmpty()){
            Vertex v = q.poll();
            v.topNum = ++counter;

            for(Edge w : v.adj){
                if(--w.destination.indegree == 0){
                    q.add(w.destination);
                }
            }
        }

        Vertex[] topologicalSort = new Vertex[vertices.size()];
        boolean isDAG = true;

        for(Vertex v : vertices){
            if(v.topNum-1 < 0){
                isDAG = false;
            } else {
                topologicalSort[v.topNum - 1] = v;
            }
        }

        if(!isDAG){
            System.out.println("The graph is not a directed acyclic graph (DAG)");
        } else {

            for (int i = 0; i < topologicalSort.length; i++) {
                System.out.print("v" + topologicalSort[i].getId());

                if(i != topologicalSort.length-1){
                    System.out.print(", ");
                }
            }

        }

        System.out.println();
        System.out.println();

    }
}

