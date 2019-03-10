package cs4150;

import java.util.*;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Corridor {
    int x; // x coordinate
    int y; // y coordinate
    double f; // the sentry factor

    public Corridor(int x, int y, double f) {
        this.x = x;
        this.y = y;
        this.f = f;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public double getf() {
        return this.f;
    }
}

class vertex implements Comparable<vertex> {
    double cost;//represents the cost from the start node to the vertex
    int vertex; //represents a vertex

    public vertex(int vertex, double cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    public int compareTo(vertex v) {
        double cost = this.cost - v.cost;
        if (cost < 0) {
            return 1;
        }
        if (cost > 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getVertex() {
        return this.vertex;
    }

    public double getCost() {
        return this.cost;
    }
}

public class GetShorty {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Corridor>> graph = new HashMap<Integer, ArrayList<Corridor>>(); // the representation of the graph
        HashMap<Integer, Double> cost = new HashMap<Integer, Double>(); // map that keeps of the track of the cost associated with each intersection


        //Create a new scanner
        //Scanner scn = new Scanner(System.in);
        Kattio io = new Kattio(System.in);
        while (io.hasMoreTokens()) {
            int n = io.getInt(); // the number of intersections
            int m = io.getInt(); // the number of corridors between intersections of x andy y with a factor f

            if (n == 0 & m == 0) {
                break;
            }

            // initialize some stuff
            for (int i = 0; i < n; i++) {
                ArrayList<Corridor> corridorList = new ArrayList<Corridor>();
                graph.put(i, corridorList);
            }

            for (int j = 0; j < m; j++) {
                int x = io.getInt();
                int y = io.getInt();
                double f = io.getDouble();
                Corridor cX = new Corridor(x, y, f); // create a new corridor
                Corridor cY = new Corridor(y, x, f);
                graph.get(x).add(cX);
                graph.get(y).add(cY);
            }

            double costOfEntry = Dijkstra(graph, cost, 0, n);
            System.out.printf("%1.4f\n", costOfEntry);
        }
    }


    public static double Dijkstra(HashMap<Integer, ArrayList<Corridor>> graph, HashMap<Integer, Double> cost, Integer start, Integer n) {
        //initialize all of the costs
        for (Integer u : graph.keySet()) {
            if (u != start) {
                cost.put(u, 0.0000);
            } else {
                cost.put(u, 1.0000);
            }
        }

        // create a new priority queue
        PriorityQueue<vertex> PQ = new PriorityQueue<vertex>();
        vertex st = new vertex(start, 1.0000);
        PQ.add(st);

        while (!PQ.isEmpty()) {
            vertex u = PQ.remove();
            int vertex = u.getVertex();
            double vertexCost = u.getCost();
            if (u.getCost() == cost.get(u.getVertex())) {
                ArrayList<Corridor> edges = graph.get(vertex);
                for (Corridor c : edges) {
                    //System.out.println("lmao"); // for debugging purposes
                    int vertexVal = c.getY();
                    if (cost.get(vertexVal) < cost.get(vertex) * c.getf()) {
                        double finalCost = cost.get(vertex) * c.getf();
                        cost.put(vertexVal, finalCost);
                        vertex v = new vertex(vertexVal, finalCost);
                        //visited.add(vertex);
                        //previous.put(vertex, u);
                        PQ.add(v);
                    }
                }
            }


        }
        return cost.get(n - 1);
    }
}
class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}

