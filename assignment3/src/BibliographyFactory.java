/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 3
 * Monday, March 30, 2020
 * */

import java.io.*;
import java.util.*;

/**
 * Programs that reads files containing references to literature in json. It extract the different
 * references from the files and validate them according to the different style of bibliography. Then
 * it format the reference that was in json in the appropriate style (IEEE, ACM, NJ) and input the reference
 * to the right file (IEEE type bibliography to IEEEi.json, ACM type bibliography to ACMi.json,
 * NJ type bibliography to NJi.json | where i is the number corresponding to the parent file (Latexi.bib) number)
 *
 * The program then ask the user for a file name. If the user input a valid file name within 2 tries. It output
 * the content of the file in the console.
 *
 * (Latex.bib files are to be placed in a directory named Comp249_W20_assg3_Files)
 *
 * @author Philippe Carrier
 */
public class BibliographyFactory {

    public static void main(String[] args) {
        System.out.println("Welcome to Bibliography Factory!");
        System.out.println();
        System.out.println();

        for (int i = 1; i <= 10; i++) {
            try (Scanner inputStream = new Scanner(
                    new FileInputStream("./Comp249_W20_Assg3_Files/Latex" + i + ".bib")
            )) {
                processFileForValidation(inputStream, i);
            } catch (FileNotFoundException e) {
                System.out.println("Could not open file Latex" + i + ".bib for reading.");
                System.out.println("Please check if file exists!");
                System.out.println("Program will terminate after closing any opened files.");
                break;
            }
        }

        System.out.println();
        File file = inputFileName();

        System.out.println();
        System.out.println("Here are the contents of the successfully created JSON File:" + file.getName());
        System.out.println();

        try (BufferedReader inputStream = new BufferedReader(new FileReader(file))) {
            String line = inputStream.readLine();
            while (line != null) {
                System.out.println(line);
                line = inputStream.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println("Goodbye! Hope you have enjoyed creating the needed files using Bibliography Factory.");
    }

    /**
     * Ask the user for a file name and return the file if the file exist. The user has 2 try
     * to input a valid file else the program will shut down.
     *
     * @return  File - the file the user inputted
     */
    private static File inputFileName() {
        try (Scanner scanner = new Scanner(System.in)) {
            int attempt = 0;
            File file;
            do {
                if(attempt > 0) {
                    System.out.println("Could not open input file. File does not exist; " +
                            "possibly could not be created");
                    System.out.println();
                    System.out.println("However, you will be allowed another chance to "+
                            "enter another file name.");
                }
                System.out.print("Please enter the name of one of the file that you need " +
                        "to review: ");
                file = new File(scanner.next());
                attempt++;
            } while (attempt < 2 && !file.exists());

            if (!file.exists()) {
                System.out.println("Could not open input file again! Either " +
                        "file does not exist or could not be created.");
                System.out.println("Sorry! I am unable to display your desired " +
                        "files! Program will exit!");
                System.exit(1);
            }
            return file;
        }
    }

    /**
     * Process the file by gathering the references within it, validating their information
     * and outputting the result to the appropriate file.
     *
     * @param inputStream  Scanner - input stream of the file where the references are written in json
     * @param index int - the file index
     */
    private static void processFileForValidation(Scanner inputStream, int index) {
        try {
            List<Map<String, String>> bibliography = new ArrayList<>();
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                if (!line.isEmpty()) {
                    Map<String, String> ref = new HashMap<>();
                    do {
                        if (!line.isEmpty() && Character.toString(line.charAt(0)).matches("[A-Za-z]")) {
                            String[] value = line.trim().split("=");
                            value[1] = value[1].replaceAll("[{},]", "");

                            ref.put(value[0], value[1]);
                        }
                        line = inputStream.nextLine();
                    } while (line.isEmpty() || line.charAt(0) != '}');
                    if (ref.isEmpty())
                        throw new FileInvalidException();
                    bibliography.add(ref);
                }

            }
            createBibliographyType("IEEE", index, bibliography);
            createBibliographyType("ACM", index, bibliography);
            createBibliographyType("NJ", index, bibliography);
        } catch (FileInvalidException e) {
            File IEEEFile = new File("./IEEE" + index + ".json");
            File ACMFile = new File("./ACM" + index + ".json");
            File NJFile = new File("./NJ" + index + ".json");

            if (IEEEFile.exists())
                IEEEFile.delete();
            if (ACMFile.exists())
                IEEEFile.delete();
            if (NJFile.exists())
                IEEEFile.delete();
        }
    }

    /**
     * Output the valid references to the appropriate file. Depending the type of the file
     *
     * @param type  String - the type of style of the bibliography
     * @param num   int - the file number
     * @param bibliography  List<Map<String, String>> - Array of key value pair map referencing the bibliography
     * @throws FileInvalidException file containing invalid or empty fields
     */
    private static void createBibliographyType(String type, int num, List<Map<String, String>> bibliography) throws FileInvalidException {
        try (PrintWriter outputStream = new PrintWriter(
                new FileOutputStream((type + num + ".json"))
        )) {
            for (int i = 0; i < bibliography.size(); i++) {
                String reference = "";
                if ("IEEE".equals(type)) {
                    reference = IEEE(bibliography.get(i));
                } else if ("ACM".equals(type)) {
                    reference = ACM(bibliography.get(i), i + 1);
                } else if ("NJ".equals(type)) {
                    reference = NJ(bibliography.get(i));
                }
                outputStream.println(reference);
                outputStream.println();
            }
        } catch(FileInvalidException e) {
            System.out.println("Error: Detected Empty Field!");
            System.out.println("============================");
            System.out.println();
            System.out.println("Problem detected with input file: Latex" + num + ".bib");
            System.out.println(e.getMessage());
            System.out.println();
            throw e;
        } catch (IOException e) {
            System.out.println("Could not opened/created file " + type + num + ".json.");
        }
    }

    /**
     * IEEE bibliography variables
     */
    public enum IEEEKeys {
        AUTHOR,
        TITLE,
        JOURNAL,
        VOLUME,
        NUMBER,
        PAGES,
        MONTH,
        YEAR
    }

    /**
     * Construction of the IEEE style bibliography with field validation.
     * @param ref   Map<String, String> - reference of the book in json format
     * @return  String - The reference of the book according to the IEEE bibliography style
     * @throws FileInvalidException file containing invalid or empty fields
     */
    private static String IEEE(Map<String, String> ref) throws FileInvalidException {
        // field validation
        for (IEEEKeys key : IEEEKeys.values()) {
            String keyString = key.toString().toLowerCase();
            validate(ref, keyString);
        }

        // reference builder
        StringBuilder s = new StringBuilder();
        String[] authors = ref.get(IEEEKeys.AUTHOR.toString().toLowerCase()).split(" and ");
        s.append(String.join(", ", authors));
        s.append(". \"");
        s.append(ref.get(IEEEKeys.TITLE.toString().toLowerCase()));
        s.append("\", ");
        s.append(ref.get(IEEEKeys.JOURNAL.toString().toLowerCase()));
        s.append(", vol. ");
        s.append(ref.get(IEEEKeys.VOLUME.toString().toLowerCase()));
        s.append(", no. ");
        s.append(ref.get(IEEEKeys.NUMBER.toString().toLowerCase()));
        s.append(", p. ");
        s.append(ref.get(IEEEKeys.PAGES.toString().toLowerCase()));
        s.append(", ");
        s.append(ref.get(IEEEKeys.MONTH.toString().toLowerCase()));
        s.append(" ");
        s.append(ref.get(IEEEKeys.YEAR.toString().toLowerCase()));
        s.append(".");
        return s.toString();
    }

    /**
     * Check if the reference of the book contains the has the appropriate variables and
     * validate that the reference is not empty.
     *
     * @param ref   Map<String, String> - reference of the book in json format
     * @param key   String - bibliography variables being tested
     * @throws FileInvalidException file containing invalid or empty fields
     */
    private static void validate(Map<String, String> ref, String key) throws FileInvalidException {
        if (!ref.containsKey(key))
            throw new FileInvalidException("File is Invalid: Field \"" +
                    key + "\" is missing. Processing stopped at this point. " +
                    "Other missing fields may be present as well!");
        if (ref.get(key).isEmpty())
            throw new FileInvalidException("File is Invalid: Field \"" +
                    key + "\" is empty. Processing stopped at this point. " +
                    "Other empty fields may be present as well!");
    }

    /**
     * ACM bibliography variables
     */
    private enum ACMKeys {
        AUTHOR,
        YEAR,
        TITLE,
        JOURNAL,
        NUMBER,
        VOLUME,
        PAGES,
        DOI
    }

    /**
     * Construction of the NJ style bibliography with validation of the fields.
     * @param ref   Map<String, String> - reference of the book in json format
     * @param index int - The index of the reference in the file [starts at 1]
     * @return  String - The reference of the book according to the ACM bibliography style
     * @throws FileInvalidException file containing invalid or empty fields
     */
    private static String ACM(Map<String, String> ref, int index) throws FileInvalidException {
        // field validation
        for (ACMKeys key : ACMKeys.values()) {
            String keyString = key.toString().toLowerCase();
            validate(ref, keyString);
        }

        // reference builder
        StringBuilder s = new StringBuilder();
        s.append("[");
        s.append(index);
        s.append("] ");
        String[] authors = ref.get(ACMKeys.AUTHOR.toString().toLowerCase()).split(" and ");
        s.append(authors[0]);
        s.append(" et al. ");
        s.append(ref.get(ACMKeys.YEAR.toString().toLowerCase()));
        s.append(". ");
        s.append(ref.get(ACMKeys.TITLE.toString().toLowerCase()));
        s.append(". ");
        s.append(ref.get(ACMKeys.JOURNAL.toString().toLowerCase()));
        s.append(". ");
        s.append(ref.get(ACMKeys.VOLUME.toString().toLowerCase()));
        s.append(", ");
        s.append(ref.get(ACMKeys.NUMBER.toString().toLowerCase()));
        s.append(" (");
        s.append(ref.get(ACMKeys.YEAR.toString().toLowerCase()));
        s.append("), ");
        s.append(ref.get(ACMKeys.PAGES.toString().toLowerCase()));
        s.append(". DOI:https://doi.org/");
        s.append(ref.get(ACMKeys.DOI.toString().toLowerCase()));
        s.append(".");
        return s.toString();
    }

    /**
     * NJ bibliography variables
     */
    private enum NJKeys {
        AUTHOR,
        TITLE,
        JOURNAL,
        VOLUME,
        PAGES,
        YEAR
    }

    /**
     * Construction of the NJ style bibliography with validation of the fields.
     * @param ref  Map<String, String> - reference of the book in json format
     * @return String - The reference of the book according to the NJ bibliography style
     * @throws FileInvalidException file containing invalid or empty fields
     */
    private static String NJ(Map<String, String> ref) throws FileInvalidException {
        // field validation
        for (NJKeys key : NJKeys.values()) {
            String keyString = key.toString().toLowerCase();
            validate(ref, keyString);
        }

        // reference builder
        StringBuilder s = new StringBuilder();
        String[] authors = ref.get(NJKeys.AUTHOR.toString().toLowerCase()).split(" and ");
        s.append(String.join(" & ", authors));
        s.append(". ");
        s.append(ref.get(NJKeys.TITLE.toString().toLowerCase()));
        s.append(". ");
        s.append(ref.get(NJKeys.JOURNAL.toString().toLowerCase()));
        s.append(". ");
        s.append(ref.get(NJKeys.VOLUME.toString().toLowerCase()));
        s.append(", ");
        s.append(ref.get(NJKeys.PAGES.toString().toLowerCase()));
        s.append("(");
        s.append(ref.get(NJKeys.YEAR.toString().toLowerCase()));
        s.append(").");
        return s.toString();
    }

}
