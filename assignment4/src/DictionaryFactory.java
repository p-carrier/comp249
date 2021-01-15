/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 4
 * Sunday, April 19, 2020
 *
 * Part 1
 * */

import java.util.ArrayList; // import 1
import java.util.Scanner; // import 2
import java.io.PrintWriter; // import 3
import java.io.FileOutputStream; // import 4
import java.io.FileInputStream; // import 5
import java.io.FileNotFoundException; // import 6

/**
 * DictionaryFactory program takes a text as an input and filter through the words
 * in the text to extract that words that can be part of the dictionary by classifying
 * them by group according to their first letter. Then, it outputs the valid words to
 * a file name SubDictionary.txt where each word will clearly be output in alphabetical
 * order.
 *
 * @author Philippe Carrier
 */
public class DictionaryFactory {

    public static void main(String[] args) {

        System.out.println("Welcome to Dictionary Factory.");
        System.out.println();
        System.out.println();

        String file = file_to_open();

        ArrayList<ArrayList<String>> dictionary = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            dictionary.add(new ArrayList<>());
        }

        fill_dictionary(file, dictionary);

        int entries = 0;
        for (ArrayList<String> letter : dictionary) {
            entries += letter.size();
        }

        dictionary_file_creation(dictionary, entries);

        System.out.println("You're file is ready.");
        System.out.println();
        System.out.println();
        System.out.println("Thank you for using the Dictionary Factory.");
    }

    /**
     * Method that ask the user for a file to be used with the program.
     *
     * @return String - the file name
     */
    private static String file_to_open() {
        System.out.print("What file would you like to open: ");
        String file = "";
        try (Scanner fileInput = new Scanner(System.in)) {
            file = fileInput.next();
            if (!file.matches(".*\\..*")) {
                throw new Exception();
            }
            return file;
        } catch (Exception e) {
            System.out.println("The name of the file you enter is not valid.");
            System.out.println("Please enter a valid file ([name].[fileType])");
            file_to_open();
        }
        return file;
    }

    /**
     * Method that fills the dictionary with the words in the file that respect the rules.
     *
     * @param file String - the name of the file
     * @param dictionary    ArrayList<ArrayList<String>> - dictionary separated by letter
     */
    private static void fill_dictionary(String file, ArrayList<ArrayList<String>> dictionary) {
        try (Scanner inputStream = new Scanner(new FileInputStream(file))){
            while(inputStream.hasNext()) {
                String word = inputStream.next().toUpperCase();
                word = word.replaceAll("[;:,.?!]", "");
                int index = word.charAt(0) - 'A';
                if (index > 25 || index < 0 ||
                        (word.matches(".*[\\d].*") &&
                                !word.matches("MC2")))
                    continue;
                if(word.length() == 1 && !word.matches("[AI]"))
                    continue;
                if (word.matches("[A-Z]*'[SM]")) {
                    word = word.split("'")[0];
                }

                if (!dictionary.get(index).contains(word)) {
                    dictionary.get(index).add(word);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("The file, " + file + ", you tried to open does not exist.");
        }
    }

    /**
     * Method that output the dictionary to a file.
     *
     * @param dictionary    ArrayList<ArrayList<String>> - dictionary separated by letter
     * @param entries   int - the number of entries in the dictionary.
     */
    private static void dictionary_file_creation(ArrayList<ArrayList<String>> dictionary, int entries) {
        try(PrintWriter outputStream = new PrintWriter(new FileOutputStream("SubDictionary.txt"))) {
            outputStream.println("The document produced this sub-dictionary, which includes " +
                    entries + " entries.");
            outputStream.println();
            for (int i = 0; i < dictionary.size(); i++) {
                outputStream.println((char) ('A' + i));
                outputStream.println("==");
                dictionary.get(i).sort(String::compareTo);
                for (String word : dictionary.get(i)) {
                    outputStream.println(word);
                }
                outputStream.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file you tried to opened, SubDictionary.txt, could not opened.");
        }
    }
}
