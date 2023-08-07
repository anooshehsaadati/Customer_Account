package ir.saadati.customeraccountrestapi2.data_access.connector;

import java.sql.Connection;

/**
 * Interface of Connection to database
 *
 * @author write with Anushe Saadati
 */
public interface IConnectionToDataBase {
    /**
     * connect to database and return connection object
     *
     * @return Connection object
     * @throws Exception Throw Exception and Stop program
     */
    Connection connectToDataBase() throws Exception;

    /**
     * disconnect to database and return nothing
     * @throws Exception Throw Exception and Stop program
     */
    void disconnectToDataBase() throws Exception;
}
