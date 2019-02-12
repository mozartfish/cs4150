package cs4150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class AutoSink {
    static int clock = 1;
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in); // Create a scanner to read in the information
        HashMap<String, Integer> cityToll = new HashMap<String, Integer>(); // map city to corresponding toll required to enter that city
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>(); // map each city to all highways associated with that city
        HashSet<String> visited = new HashSet<String>(); // set that contains all the vertices in the graph that have been visited
        HashMap<String, Integer> previsit = new HashMap<String, Integer>(); // map that maps each city with its previsit time
        HashMap<String, Integer> postvisit = new HashMap<String, Integer>(); // map that maps each city with its postvisit time
        // a counter for keeping track of the previsit and postvisit times
        ArrayList<String> topOrderedCities = new ArrayList<String>(); // list that stores all the cities in topological order
        HashMap<String, Integer> minCost = new HashMap<String, Integer>(); //map that maps the cost of a vertex to the sink node

        int n = scn.nextInt(); // the number of cities on the map
        for (int i = 0; i < n; i++) {
            String city = scn.next();
            int toll = scn.nextInt();
            cityToll.put(city, toll);
        }

        for (String city: cityToll.keySet())
        {
            ArrayList<String> edges = new ArrayList<String>();
            graph.put(city,edges);
        }

        int h = scn.nextInt(); // the number of highways on the map
        for (int j = 0; j < h; j++) {
            String city = scn.next();
            if (!graph.containsKey(city)) {
                ArrayList<String> highways = graph.get(city);
                highways.add(scn.next());
                graph.put(city, highways);
            } else {
                ArrayList<String> highways = graph.get(city);
                highways.add(scn.next());
                graph.put(city, highways);
            }
        }

        dfs(graph, visited, previsit, postvisit, topOrderedCities); // determine the topological ordering for the cities

        int t = scn.nextInt(); // the number of trips


        for (int k = 0; k < t; k++)
        {
            String source = scn.next();
            String  sink = scn.next();

            if (source.equals(sink)) // check to see if the source and the sink are the same city
            {
                System.out.println(0);
            }
            else
            {
                int index = topOrderedCities.indexOf(sink) + 1;

                for (int d = 0; d < topOrderedCities.size(); d++)
                {
                    if (topOrderedCities.get(d).equals(sink))
                    {
                        String sinkCity = topOrderedCities.get(d);
                        minCost.put(sinkCity, 0);
                    }
                    else
                    {
                        String otherCity = topOrderedCities.get(d);
                        minCost.put(otherCity, Integer.MAX_VALUE);
                    }
                }

                for (int g = index; g <= topOrderedCities.indexOf(source); g++)
                {
                    int tollCost = Integer.MAX_VALUE;
                    String city = topOrderedCities.get(g);
                    ArrayList<String> edges = graph.get(city); // get all the edges associated with the city at the specific index
                    for (String edge :edges)
                    {
                        if (minCost.get(edge) == Integer.MAX_VALUE)
                        {
                            continue;
                        }
                        else
                        {
                            tollCost = Math.min(cityToll.get(edge) + minCost.get(edge), tollCost);
                        }
                    }
                    minCost.put(city, tollCost);
                }

                if (minCost.get(source) == Integer.MAX_VALUE)
                {
                    System.out.println("NO");
                }
                else
                {
                    System.out.println(minCost.get(source));
                }

            }
        }

    }

    public static void dfs(HashMap<String, ArrayList<String>> graph, HashSet<String> visited, HashMap<String, Integer> previsit, HashMap<String, Integer> postvisit, ArrayList<String> topOrderedCities)
    {
        //iterate through all the vertices in the graph and visit each vertex in the graph
        for (String vertex: graph.keySet())
        {
            if (!visited.contains(vertex))
            {
                explore(graph, vertex, visited, previsit, postvisit, topOrderedCities);
            }
        }
    }

    public static void explore(HashMap<String, ArrayList<String>>graph, String vertex, HashSet<String> visited, HashMap<String, Integer> previsit, HashMap<String, Integer> postvisit, ArrayList<String> topOrderedCities)
    {
        //mark the vertex as visited
        visited.add(vertex);

        //update previsit time for the vertex
        previsit(vertex, previsit);

        //iterate through all the edges associated with the vertex
        ArrayList<String> edges = graph.get(vertex);

        if (edges.size() == 0)
        {
            //update the postvisit time for the vertex
            topOrderedCities.add(vertex);
            postvisit(vertex, postvisit);
        }
        else
        {
            for (String u: edges)
            {
                if (!visited.contains(u))
                {
                    explore(graph, u, visited, previsit, postvisit, topOrderedCities);

                }
            }
            topOrderedCities.add(vertex);
            postvisit(vertex, postvisit);
        }
    }

    public static void previsit(String vertex, HashMap<String, Integer> previsit)
    {
        previsit.put(vertex, clock);
        clock = clock + 1;
    }

    public static void postvisit(String vertex, HashMap<String, Integer> postvisit)
    {
        postvisit.put(vertex, clock);
        clock = clock + 1;
    }
}