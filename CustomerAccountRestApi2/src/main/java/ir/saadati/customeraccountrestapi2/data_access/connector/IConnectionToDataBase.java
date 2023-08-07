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
     */
    Connection connectToDataBase();

    /**
     * disconnect to database and return nothing
     */
    void disconnectToDataBase();
}
