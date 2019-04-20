package cs4150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.TreeSet;

public class TravelingSalesman {
    public static void main(String[] args)
    {
        Kattio io = new Kattio(System.in); // create a new scanner object to read stuff in

        int n = io.getInt(); // the number of vertices
        int[][] graph = new int[n][n]; // create a new graph using an adjacency matrix representation
        int[][] cache = new int[n][(int) Math.pow(2, n)];
        //populate the graph
        for (int row = 0; row < n; row++)
        {
            for (int column = 0; column < n; column++)
            {
                graph[row][column] = io.getInt();
            }
        }

        for (int cacheRow = 0; cacheRow < n; cacheRow++)
        {
            for (int cacheColumn = 0; cacheColumn < (int)Math.pow(2, n); cacheColumn++)
            {
                cache[cacheRow][cacheColumn] = Integer.MAX_VALUE;
            }
        }

        System.out.println(travel(graph, n));
    }


  public static int[] travel(int[][] graph, int numVertices)
  {
      int minRowVal = Integer.MAX_VALUE;
      int[] minRowVals = new int[numVertices];
      //iterate over all the rows and columns
      for (int row = 0; row < numVertices; row++) {
          for (int column = 0; column < numVertices; column++) {
              if (graph[row][column] < minRowVal) {
                  minRowVal = graph[row][column];
              }
          }
          minRowVals[row] = minRowVal;
      }

      return minRowVals;
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
