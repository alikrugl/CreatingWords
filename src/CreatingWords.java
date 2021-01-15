import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * File: CreatingWords.java
 * <p>
 * This program of 3 entered letters makes a words and print them.
 * The order of entering letters is the same as the order of these letters in the word.
 * Works only in English.
 * <p>
 */
public class CreatingWords {

    /** The name and location of the dictionary file. */
    private static final String DICTIONARY_FILE1 = "assets/en-dictionary.txt";

    /** Much more words*/
    private static final String DICTIONARY_FILE2 = "assets/English Dictionary.txt";

    /** List of words from DICTIONARY_FILE */
    private static final ArrayList<String> STRINGS_FROM_DICTIONARY = new ArrayList<>();

    public static void main(String[] args) {

        /* Write all dictionary words to list*/
        try {
            /* Open the file for reading. */
            BufferedReader bufferedReader = new BufferedReader(new FileReader(DICTIONARY_FILE1));
            while (true) {
                String word = bufferedReader.readLine();

                /* no data left*/
                if (word == null)
                    break;

                STRINGS_FROM_DICTIONARY.add(word);
            }

            BufferedReader bufferedReaderIn = new BufferedReader(new InputStreamReader(System.in));

            /* Repeatedly prompt the user for a 3 letters
             * and print out the words that can be created by this letters.
             */
            while (true) {

                /* 3 entered letters in a row. Case is not important. */
                System.out.println("Enter 3 english letters in a row : ");
                String letters = bufferedReaderIn.readLine();
                while (letters.length() != 3) {
                    System.out.println("Please, enter 3 english letters in a row : ");
                    letters= bufferedReaderIn.readLine();
                }
                System.out.println("Words that can be created by letters " + "\"" + letters + "\"" + ": " + "\n");

                ArrayList<String> createdWords = createWordsByEnteredLetters(letters);
                for (String word : createdWords)
                    System.out.println(word);

                System.out.println();
                if (createdWords.size() == 0)
                    System.out.println("No words can be created by this letters." + "\n");
            }
        }catch (IOException e) {
            System.out.println("Some problem with input");
        }
    }

    /**
     * Adds words to the list if they can be created by 3 entered letters.
     * Words must contain this letters in a entered order.
     *
     * @param letters entered letters
     * @return ArrayList of strings that contains all words that can be created by 3 given letters.
     */
    private static ArrayList<String> createWordsByEnteredLetters(String letters) {
        ArrayList<String> result = new ArrayList<>();

        for (String currentWord : STRINGS_FROM_DICTIONARY) {
            if (currentWord == null)
                continue;

            if (isWordCorrect(currentWord, letters))
                result.add(currentWord);
        }
        return result;
    }

    /**
     * Checks if the variable "word" can be created by 3 given letters. Letters must be in a given order.
     *
     * @param currentWord the word that hypothetically can by created by given letters.
     * @param letters     given(entered) letters.
     * @return true if the word can be created by given letter and false in other cases.
     */
    private static boolean isWordCorrect(String currentWord, String letters) {

        char[] enteredLetters = letters.toLowerCase().toCharArray();

        for (int i = 0; i < currentWord.length(); i++) {
            if (enteredLetters[0] == currentWord.charAt(i)) {
                for (int j = i + 1; j < currentWord.length(); j++) {
                    if (enteredLetters[1] == currentWord.charAt(j)) {
                        for (int k = j + 1; k < currentWord.length(); k++) {
                            if (enteredLetters[2] == currentWord.charAt(k))
                                return true;   /* all conditions of the creating the word are met*/
                        }
                    }
                }
            }
        }
        return false;
    }
}
