package data_access.connector;

import java.sql.Connection;

public interface IConnectionToDataBase {
    Connection connectToDataBase() throws Exception;

    void disconnectToDataBase() throws Exception;
}
