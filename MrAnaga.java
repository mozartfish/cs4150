package cs4150;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MrAnaga {
    public static void main(String[] args) {
        String[] g = wordListGenerator(2000, 10240);
        //create a stopwatch
        long startTime = System.nanoTime();

        double elapsed = 0;
        long repetitions = 1;

        do {
            repetitions *= 2;
            startTime = System.nanoTime();
            for (long i = 0; i < repetitions; i++)
            {
                MrAnaga(g);
            }
            elapsed = System.nanoTime() - startTime;
        } while (elapsed < 1.0e9);

        double totalAverage = elapsed / repetitions;

        startTime = System.nanoTime();
        elapsed = 0;
        repetitions = 1;

        do {
            repetitions *= 2;
            startTime = System.nanoTime();
            for (long i = 0; i < repetitions; i++)
            {
                //MrAnaga(g);
            }
            elapsed = System.nanoTime() - startTime;
        } while (elapsed < 1.0e9);
        double overheadAverage = elapsed / repetitions;
        double timeDifference = totalAverage - overheadAverage;

        System.out.println("Total Average: " + totalAverage + " nanoseconds");
        System.out.println("Overhead Average: " + overheadAverage + " nanoseconds");
        System.out.println("The time difference " + timeDifference + " nanoseconds");

    }

    public static void MrAnaga(String[] s)
    {
        //Create a scanner to read in the input
        //Scanner scn = new Scanner(System.in);

        //Hashtable storing the unique words
        HashMap<String, Integer> wordList = new HashMap<String, Integer>();

        //variable that stores the number of unique words that do not have anagrams
        int numUniqueWords = 0;

        //Read in the first two integers
        //int n = scn.nextInt(); // the number of words
        //int k = scn.nextInt(); // the number of lower case letters in the word

        for (int i = 0; i < s.length; i++)
        {
            String p = s[i];

            if (!wordList.containsKey(alphabetSort(p)))
            {
                wordList.put(alphabetSort(p), 1);
            }
            else {
                wordList.put(alphabetSort(p), wordList.get(alphabetSort(p)) + 1);
            }

        }
        //scn.nextLine();
        /**while (scn.hasNextLine())
         {

         */

        for (String wordCount: wordList.keySet())
        {
            if (wordList.get(wordCount) == 1)
            {
                numUniqueWords++;
            }
        }
        //System.out.println(numUniqueWords);
    }

    public static String alphabetSort(String s)
    {
        char alphabetWord []= s.toCharArray();
        Arrays.sort(alphabetWord);
        return new String(alphabetWord);
    }

    public static String[] wordListGenerator(int n, int k)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        String[] wordList = new String[n];

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++)
        {
            str = new StringBuilder();

            for (int j = 0; j < k; j++)
            {
                int index = (int)(Math.random() * 26);
                str.append(alphabet.charAt(index));
            }

            wordList[i] = str.toString();
        }
        return wordList;
    }
}