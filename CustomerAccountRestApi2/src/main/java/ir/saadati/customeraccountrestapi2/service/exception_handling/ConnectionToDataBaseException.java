package ir.saadati.customeraccountrestapi2.service.exception_handling;

/**
 * Handling Connection to DataBase Exception
 * this class extends from Exception class
 *
 * @author write with Anushe Saadati
 */
public class ConnectionToDataBaseException extends Exception {
    /**
     * Constructs a new exception without any input
     */
    public ConnectionToDataBaseException() {
    }

    /**
     * Handling connecting to database with exception
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ConnectionToDataBaseException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public ConnectionToDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
