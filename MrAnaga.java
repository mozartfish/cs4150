package cs4150;

import java.util.*;
public class MrAnaga {
    public static void main(String[] args) {
        //Create a scanner to read in the input
        Scanner scn = new Scanner(System.in);

        //Hashtable storing the unique words
        HashMap<String, Integer> wordList = new HashMap<String, Integer>();

        //variable that stores the number of unique words that do not have anagrams
        int numUniqueWords = 0;

        //Read in the first two integers
        int n = scn.nextInt(); // the number of words
        int k = scn.nextInt(); // the number of lower case letters in the word

        scn.nextLine();
        while (scn.hasNextLine())
        {
            String p = scn.nextLine();
            if (!wordList.containsKey(alphabetSort(p)))
            {
                wordList.put(alphabetSort(p), 1);
            }
            else {
                wordList.put(alphabetSort(p), wordList.get(alphabetSort(p)) + 1);
            }
        }

        for (String wordCount: wordList.keySet())
        {
            if (wordList.get(wordCount) == 1)
            {
                numUniqueWords++;
            }
        }
        System.out.println(numUniqueWords);
    }

    public static String alphabetSort(String s)
    {
        char alphabetWord []= s.toCharArray();
        Arrays.sort(alphabetWord);
        return new String(alphabetWord);
    }
}
