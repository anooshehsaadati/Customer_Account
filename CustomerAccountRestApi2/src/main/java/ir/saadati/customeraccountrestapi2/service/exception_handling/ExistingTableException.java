package ir.saadati.customeraccountrestapi2.service.exception_handling;

/**
 * Handling Not Existing table of database Exception
 * this class extends from Exception class
 *
 * @author write with Anushe Saadati
 */
public class ExistingTableException extends Exception {
    /**
     * Constructs a new exception without any input
     */
    public ExistingTableException() {
    }

    /**
     * Handling not exist table of database with exception
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ExistingTableException(String message) {
        System.out.println("Table " + message + " Not exist. please create table and try again.");
    }
}
