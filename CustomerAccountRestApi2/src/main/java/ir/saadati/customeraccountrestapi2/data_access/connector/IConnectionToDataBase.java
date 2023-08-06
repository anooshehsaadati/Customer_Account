package ir.saadati.customeraccountrestapi2.data_access.connector;

import java.sql.Connection;

/**
 * Interface of Connection to database with handling Exception
 *
 * @author write with Anushe Saadati
 */
public interface IConnectionToDataBase {
    /**
     * connect to database and return connection object
     *
     * @return Connection object
     * @throws Exception connecting to database
     */
    Connection connectToDataBase() throws Exception;

    /**
     * disconnect to database and return nothing
     *
     * @throws Exception disconnecting to database
     */
    void disconnectToDataBase() throws Exception;
}
