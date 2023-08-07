package ir.saadati.customeraccountrestapi2.service.exception_handling;

/**
 * Handling Connection to DataBase Exception
 * this class extends from Exception class
 *
 * @author write with Anushe Saadati
 */
public class ConnectionToDataBaseException extends Exception{
    /**
     * Handling connecting to database with exception
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ConnectionToDataBaseException(String message) {
        super(message);
    }
}
