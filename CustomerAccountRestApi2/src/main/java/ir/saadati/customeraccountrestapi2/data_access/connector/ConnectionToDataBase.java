package ir.saadati.customeraccountrestapi2.data_access.connector;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class of Connection to database with handling Exception implements from IConnectionToDataBase
 *
 * @author write with Anushe Saadati
 */
public class ConnectionToDataBase implements IConnectionToDataBase {
    private Connection con;

    /**
     * connect to database and return connection object
     *
     * @return Connection object
     * @throws Exception connecting to database
     */
    @Override
    public Connection connectToDataBase() throws Exception {
        String url = "jdbc:mysql://localhost:3306/account_customer_interview";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * disconnect to database and return nothing
     *
     * @throws Exception disconnecting to database
     */
    @Override
    public void disconnectToDataBase() throws Exception {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}