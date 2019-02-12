package cs4150;

import java.util.Scanner;

class Star {
    long xCoordinate;
    long yCoordinate;

    public Star(long xCoordinate, long yCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
public class GalaxyQuest {
    public static void main(String[] args) {
        //create a scanner to read in the information
        Scanner scn = new Scanner(System.in);
        long diameter = scn.nextLong(); // planetary universe galactic diameter
        long k = scn.nextLong(); // the total number of stars in the planetary universe

        Star[] stars = new Star[(int)k];

        for (long i = 0; i < stars.length; i++)
        {
            long xCoordinate = scn.nextLong();
            long yCoordinate = scn.nextLong();
            Star star = new Star(xCoordinate, yCoordinate);
            stars[(int)i] = star;
        }

        Star champion = findMajorityElement(stars, diameter);
        long count = 0;

        for (long n = 0; n < stars.length; n++)
        {
            if (inSameGalaxy(champion, stars[(int)n], diameter))
            {
                count++;
            }
        }

        if (count > k/2)
        {
            System.out.println(count);
        }
        else
        {
            System.out.println("NO");
        }
    }

    public static long starDistance (Star a, Star b)
    {
        long xCoordinateDiff = (long)Math.pow((a.xCoordinate - b.xCoordinate), 2);
        long yCoordinateDiff = (long)Math.pow((a.yCoordinate - b.yCoordinate), 2);
        return xCoordinateDiff + yCoordinateDiff;
    }

    public static boolean inSameGalaxy(Star a, Star b, long diameter)
    {
        long diameterSquared = (long)Math.pow(diameter, 2);
        return starDistance(a, b) <= diameterSquared;
    }

    public static Star findMajorityElement(Star[] stars, long diameter)
    {
        Star champion = null;
        long count =  0;

        for (long i = 0; i < stars.length; i++)
        {
            if (count == 0)
            {
                champion = stars[(int)i];
                count = 1;
            }

            else if (inSameGalaxy(champion, stars[(int)i], diameter))
            {
                count++;
            }
            else
            {
                count--;
            }
        }

        return champion;
    }

}
