package cs4150;

import java.util.*;


public class RumorMill {
    public static void main(String[] args) {
        HashMap<String, Integer> distance = new HashMap<String, Integer>(); // store the distance associated with every vertex
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>(); // representation of the graph
        TreeMap<Integer, TreeSet<String>> orderedMap = new TreeMap<Integer, TreeSet<String>>();// a graph with all the vertices and distances in sorted order

        Scanner scn = new Scanner(System.in); // create a new scanner

        int n = scn.nextInt(); // the number of students at the school

        for (int i = 0; i < n; i++)
        {
            String name = scn.next();
            ArrayList<String> friends = new ArrayList<String>();
            graph.put(name, friends);
        }

        int f = scn.nextInt(); // the number of friends

        for (int j = 0; j < f; j++)
        {
            String name = scn.next();
            String friend = scn.next();
            ArrayList<String> friends_name = graph.get(name);
            ArrayList<String> friends_friend = graph.get(friend);
            friends_name.add(friend);
            friends_friend.add(name);
            graph.put(name, friends_name);
            graph.put(friend, friends_friend);
        }

        int r = scn.nextInt(); // the number of rumors

        for (int k = 0; k < r; k++)
        {
            String name = scn.next();
            bfs(graph, name, distance); // perform the bfs for this person
            for (Integer s: distance.values())
            {
                TreeSet<String> orderedNames = new TreeSet<String>();
                orderedMap.put(s, orderedNames);
            }
            for (Map.Entry<String,Integer> entry: distance.entrySet())
            {
                int key = entry.getValue();
                String value = entry.getKey();
                orderedMap.get(key).add(value);
            }
            for (Integer key: orderedMap.keySet())
            {
                TreeSet<String> names = orderedMap.get(key);
                for (String l: names)
                {
                    System.out.println(l);
                }
            }
            orderedMap.clear();
        }
    }


    public static void bfs(HashMap<String, ArrayList<String>> graph, String start, HashMap<String, Integer> distance)
    {
       for (String vertex: graph.keySet()) {
           distance.put(vertex, Integer.MAX_VALUE);
       }
       int dist = distance.get(start);
       dist = 0;
       distance.put(start,dist);

       LinkedList<String> queue = new LinkedList<String>();

       queue.add(start);

       while (!queue.isEmpty())
       {
           String u = queue.poll();
           ArrayList<String> edges = graph.get(u);
           for (String vertex: edges)
           {
               if (distance.get(vertex) == Integer.MAX_VALUE)
               {
                   queue.add(vertex);
                   int distanceVertex = distance.get(vertex);
                   distanceVertex = distance.get(u) + 1;
                   distance.put(vertex, distanceVertex);
               }
           }
       }
    }
}


