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

public class UnderTheRainbow
{
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in); // initialize a new kattio object
        int n = io.getInt(); // number of elements
        int[] distances = new int[n + 1]; // initialize an array to store elements
        HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
        cache.put(n, 0);
        for (int i = 0; i < distances.length; i++) {
            int dist = io.getInt();
            distances[i] = dist;
        }
        System.out.println(DynamicProgrammingCalculation(distances, n, 0, cache));
    }

    public static int DynamicProgrammingCalculation(int[] distances, int numStops, int currentLocation, HashMap<Integer, Integer> cache) // n represents the number of stops anjd
    {
        int minPenalty = (int)Math.pow(10, 20);

//        if (numStops == currentLocation)
//        {
//            return 0;
//        }

        if (cache.containsKey(currentLocation))
        {
            return cache.get(currentLocation);
        }

        for (int k = currentLocation + 1; k < distances.length; k++)
        {

            //int penaltyCost = (int) Math.pow(400 - (distances[k] - distances[currentLocation]), 2)+ DynamicProgrammingCalculation(distances, numStops, k, cache);
            int penaltyCost =(int) Math.pow(400 - (distances[k] - distances[currentLocation]), 2) + DynamicProgrammingCalculation(distances, numStops, k, cache);
            minPenalty = Math.min(minPenalty, penaltyCost);
        }
        cache.put(currentLocation, minPenalty);
        return minPenalty;
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


