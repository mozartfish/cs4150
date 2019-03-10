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

public class NumberTheory {
    public static void main(String[] args)
    {
        Kattio io = new Kattio(System.in);
        while (io.hasMoreTokens())
        {
            String operation = io.getWord();
            if (operation.equals("gcd"))
            {
                long a = io.getInt();
                long b = io.getInt();
                System.out.println(gcd(a,b));
            }
            else if (operation.equals("exp"))
            {
                long x = io.getInt();
                long y = io.getInt();
                long N = io.getInt();
                System.out.println(modexp(x,y,N));
            }
            else if (operation.equals("inverse"))
            {
                long a = io.getInt();
                long N = io.getInt();
                long inverse = inverse(a,N);
                if (inverse > 0)
                {
                    System.out.println(inverse);
                }
                else {
                    System.out.println("none");
                }
            }
            else if (operation.equals("isprime"))
            {
                long N = io.getInt();
                System.out.println(isPrime(N));
            }
            else
            {
                long p = io.getInt();
                long q = io.getInt();
                long[] RSA = RSA(p, q);
                System.out.println(RSA[0] + " " + RSA[1] + " " + RSA[2]);
            }
        }

    }

    public static long gcd (long a, long b)
    {
        while (b > 0)
        {
            long aModB = a % b;
            a = b;
            b = aModB;
        }
        return a;
    }

    public static long modexp(long x, long y, long N)
    {
        if (y == 0)
        {
            return 1;
        }
        else
        {
            long z = modexp(x, y/2, N);
            if (y % 2 == 0)
            {
                // z^2 = z * z
                // z^2 mod N = (z mod N) * (z mod N) mod N

                return((z % N) * (z % N)) % N;

            }
            else
            {
                // split the multiplication into two mod multiplications since we have three terms
                // = (x * z^2) mod N = (x * z * z) mod N
                // = (((x mod N) * (z mod N) mod N) * (z mod N)) mod N
                return ((((x % N) * (z % N)) % N) * (z % N)) % N;

            }
        }
    }

    public static long[] ee(long a, long b)
    {
        long[] foo = new long[3];
        if (b == 0)
        {
            foo[0] = 1;
            foo[1] = 0;
            foo[2] = a;
            return foo;
        }
        else
        {
            long[] bar = ee(b, a % b);
            foo[0] = bar[1];
            foo[1] = bar[0] - (a/b)*bar[1];
            foo[2] = bar[2];
            return foo;
        }
    }

    public static long inverse (long a, long N)
    {
        long[]foo = ee(a, N);
        if (foo[2] == 1)
        {
            // if we get a negative value we have to add by the number we are doing mod with in order to satisfy the definition of mod
            if (foo[0] < 0)
            {
                foo[0] = foo[0] + N;
            }
            return (foo[0] % N);
        }
        else
        {
            return -1;
        }
    }

    public static String isPrime (long N)
    {
        int a = 2;
        int b = 3;
        int c = 5;

        long modA = modexp(a, N-1, N) % N;
        long modB = modexp(b, N-1, N) % N;
        long modC = modexp(c, N-1, N) % N;

        if ((modA == 1) && (modB == 1) & (modC == 1))
        {
            return "yes";
        }
        else
        {
            return "no";
        }
    }

    public static long[] RSA (long p, long q)
    {
        long N = p * q;
        long phi = (p - 1) * (q - 1);
        long e = 2;
        long d = 0;
        long result[] = new long[3];
        while (gcd(e, phi) != 1)
        {
            e++;
            //System.out.println(e);
        }

        //e * d = 1 mod phi
        // then d is the multiplicative inverse of e and phi as defined by the def of multiplicative inverse
        d = inverse(e, phi);

        result[0] = N;
        result[1] = e;
        result[2] = d;
        return result;
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


