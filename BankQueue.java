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




class Customer implements Comparable<Customer> {
    int money; // the money associated with the customer
    int waitTime; // the time a customer has to wait

    public Customer(int dollars, int waitTime) {
        this.money = dollars;
        this.waitTime = waitTime;
    }

    public int compareTo(Customer c) {
        int moneyDifference = this.money - c.money;

        if (moneyDifference > 0) {
            return -1;
        }
        if (moneyDifference < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getMoney() {
        return this.money;
    }

    public int getwaitTime() {
        return this.waitTime;
    }
}

public class BankQueue {
    public static void main(String[] args)
    {
        Kattio io = new Kattio(System.in);
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        while (io.hasMoreTokens())
        {
            int N = io.getInt(); // the number of people
            int T = io.getInt(); // the number of minutes until the bank closes

            for (int g = 0; g < N; g++)
            {
                int dollars = io.getInt();
                int time = io.getInt();
                Customer c = new Customer(dollars, time);
                customerList.add(c);
            }

            System.out.println(maxMoney(customerList, T));
        }
    }

    public static int maxMoney(ArrayList<Customer> customerList, int timeConstraint)
    {
        int[] maxMoney = new int[timeConstraint + 1];
        int totalMoney = 0;

        //Sort the customer list into descending order
        Collections.sort(customerList);

        for (int i = 0; i < customerList.size(); i++)
        {
            Customer foo = customerList.get(i);

            if (foo.getwaitTime() > maxMoney.length)
            {
                for (int m = maxMoney.length; m >= 0; m--)
                {
                    if (foo.getMoney() > maxMoney[m])
                    {
                        maxMoney[m] = foo.getMoney();
                        break;
                    }
                }
            }
            else
            {
                for (int n = foo.getwaitTime(); n >= 0; n--)
                {
                    if (foo.getMoney() > maxMoney[n])
                    {
                        maxMoney[n] = foo.getMoney();
                        break;
                    }
                }
            }
        }

        for (int j =0; j < maxMoney.length; j++)
        {
            totalMoney = totalMoney + maxMoney[j];
        }

        return totalMoney;
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











