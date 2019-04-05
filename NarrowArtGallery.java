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

public class NarrowArtGallery {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in); // create a new io object to read in input
        int N = io.getInt(); // the number of rows
        int k = io.getInt(); // the number of rooms that must be closed off
        int[][] narrowArtGallery = new int[N][2];
        HashMap<String, Integer> cache = new HashMap<String, Integer>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                int p = io.getInt();
                narrowArtGallery[i][j] = p;
            }
        }

        System.out.println(maxValue(0, -1, k, N, narrowArtGallery, cache));

    }

    public static int maxValue(int r, int uncloseableRoom, int k, int N, int[][] narrowArtGallery, HashMap<String, Integer> cache) {
        int maxMoney = 0; // variable for storing the maximum value


        String s = r + " " + k + " " + uncloseableRoom;

        if (cache.containsKey(s))
        {
            return cache.get(s);
        }

        //base case
        if (r == N)
        {
            return 0;
        }

        // CASE 1:  K == N - r
        else if (k == N - r)
        {
            if (uncloseableRoom == 0)
            {
                maxMoney = narrowArtGallery[r][0] + maxValue(r + 1, 0, k - 1, N, narrowArtGallery, cache);
                cache.put(s, maxMoney);
                return maxMoney;
            }
            else if (uncloseableRoom == 1)
            {
                maxMoney = narrowArtGallery[r][1] + maxValue(r + 1, 1, k - 1, N, narrowArtGallery, cache);
                cache.put(s, maxMoney);
                return maxMoney;
            }
            // k == -1
            else
            {
                int g = narrowArtGallery[r][0] + maxValue(r + 1, 0, k - 1, N, narrowArtGallery, cache);
                int q = narrowArtGallery[r][1] + maxValue(r + 1, 1, k - 1, N, narrowArtGallery, cache);

                maxMoney = Math.max(g, q);
                cache.put(s, maxMoney);
                return maxMoney;
            }
        }

        //CASE 2: k < N -r
        else
        {
            if (uncloseableRoom == 0)
            {
               int p = narrowArtGallery[r][0] + maxValue(r + 1, 0, k - 1, N, narrowArtGallery, cache);
               int l = narrowArtGallery[r][0] + narrowArtGallery[r][1] + maxValue(r + 1, -1, k, N, narrowArtGallery, cache);
               maxMoney = Math.max(p , l);
               cache.put(s, maxMoney);
               return maxMoney;
            }
            else if (uncloseableRoom == 1)
            {
                int f = narrowArtGallery[r][1] + maxValue(r + 1, 1, k - 1, N, narrowArtGallery, cache);
                int w = narrowArtGallery[r][0] + narrowArtGallery[r][1] + maxValue(r + 1, -1, k, N, narrowArtGallery, cache);
                maxMoney = Math.max(f, w);
                cache.put(s, maxMoney);
                return maxMoney;
            }
            else
            {
                int a = narrowArtGallery[r][0] + maxValue(r + 1, 0, k - 1, N, narrowArtGallery, cache);
                int d = narrowArtGallery[r][1] + maxValue(r + 1, 1, k - 1, N, narrowArtGallery, cache);
                int j = Math.max(a, d);
                int e = narrowArtGallery[r][0] + narrowArtGallery[r][1] + maxValue(r + 1, -1, k, N, narrowArtGallery, cache);
                maxMoney = Math.max(e, j);
                cache.put(s, maxMoney);
                return maxMoney;
            }
        }

//        // k = 0
//        else
//        {
//            maxMoney = narrowArtGallery[r][0] + narrowArtGallery[r][1] + maxValue(r + 1, -1, k, N, narrowArtGallery);
//            return maxMoney;
//        }
    }
}

/**
 * Simple yet moderately fast I/O routines.
 * <p>
 * Example usage:
 * <p>
 * Kattio io = new Kattio(System.in, System.out);
 * <p>
 * while (io.hasMoreTokens()) {
 * int n = io.getInt();
 * double d = io.getDouble();
 * double ans = d*n;
 * <p>
 * io.println("Answer: " + ans);
 * }
 * <p>
 * io.close();
 * <p>
 * <p>
 * Some notes:
 * <p>
 * - When done, you should always do io.close() or io.flush() on the
 * Kattio-instance, otherwise, you may lose output.
 * <p>
 * - The getInt(), getDouble(), and getLong() methods will throw an
 * exception if there is no more data in the input, so it is generally
 * a good idea to use hasMoreTokens() to check for end-of-file.
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
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
