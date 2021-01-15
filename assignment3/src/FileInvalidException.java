/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 3
 * Monday, March 30, 2020
 * */

/**
 *  Error concerning the validity of the fields in the reference of the bibliography.
 *
 * @author Philippe Carrier
 */
public class FileInvalidException extends Exception {

    /**
     * Default constructor
     */
    public FileInvalidException() {
        super("Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)");
    }

    /**
     * Constructor with 1 parameter
     * @param message String - message concerning the error.
     */
    public FileInvalidException(String message) {
        super(message);
    }
}
