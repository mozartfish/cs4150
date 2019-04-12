package cs4150;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class SpidermansWorkout {
    public static void main(String[] args) {
        HashMap<String, Integer> cache = new HashMap<String, Integer>(); // the cache
        Kattio io = new Kattio(System.in); // create a new kattio object to read in input
        int N = io.getInt(); // the number of test scenarios
        for (int i = 0; i < N; i++) {
            int M = io.getInt(); // the number of distances
            int[] distances = new int[M]; // create an array to store all the values for the distances
            for (int j = 0; j < M; j++) {
                distances[j] = io.getInt();
            }

            int stuff = workOut(distances, 0, 0, cache);
            int currentHeight = 0;
            String s = "";

            String p = 0 + " " + 0;

            if (cache.get(p) == Integer.MAX_VALUE)
            {
                System.out.println("IMPOSSIBLE");
            }
            else
            {
                for (int h = 0; h < distances.length; h++)
                {
                    String up = (h + 1) + " " + (currentHeight + distances[h]);
                    String down = (h + 1) + " " + (currentHeight - distances[h]);
                    Integer upValue = cache.get(up);
                    Integer downValue = cache.get(down);

                    if (currentHeight - distances[h] < 0)
                    {
                        s+= "U";
                        currentHeight = currentHeight + distances[h];
                        continue;
                    }

                    int minValue = Math.min(upValue, downValue);

                    if (minValue == upValue)
                    {
                        s+= "U";
                        currentHeight = currentHeight + distances[h];
                    }
                    else
                    {
                        s+= "D";
                        currentHeight = currentHeight - distances[h];
                    }
                }
            }
            System.out.println(s);
            cache.clear();
        }
    }
    public static int workOut(int[] distances, int currentLocation, int currentHeight, HashMap<String, Integer> cache)
    {
        String s = currentLocation + " " + currentHeight; // create a key for the cache

        //check if the cache already contains the value
        if (cache.containsKey(s))
        {
            return cache.get(s);
        }

        //check to see if we are below ground
        if (currentHeight < 0)
        {
            cache.put(s, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }

        //check to see if we are at the end of the array
        if (currentLocation == distances.length)
        {
            if (currentHeight > 0)
            {
                cache.put(s, Integer.MAX_VALUE);
                return Integer.MAX_VALUE;
            }
            else
            {
                cache.put(s, 0);
                return 0;
            }
        }
        else
        {
            //climbing up
            int climbUp = workOut(distances, currentLocation + 1, currentHeight + distances[currentLocation], cache);
            int maxClimbUp = Math.max(climbUp, currentHeight);

            //climbing down
            int climbDown = workOut(distances, currentLocation + 1, currentHeight - distances[currentLocation], cache);
            int maxClimbdown = Math.max(climbDown, currentHeight);

            cache.put(s, Math.min(maxClimbUp, maxClimbdown));
            return Math.min(maxClimbUp, maxClimbdown);
        }
    }


}

/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */


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
