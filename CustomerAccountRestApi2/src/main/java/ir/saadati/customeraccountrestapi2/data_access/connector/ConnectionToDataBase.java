package ir.saadati.customeraccountrestapi2.data_access.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class of Connection to database with handling Exception implements from IConnectionToDataBase
 * Handling the Checked/Unchecked Exception
 *
 * @author write with Anushe Saadati
 */
public class ConnectionToDataBase implements IConnectionToDataBase {
    private Connection con;

    /**
     * connect to database and return connection object
     * Handle Class Not Found Exception and SQL Exception and Unknown exception
     *
     * @return Connection object
     */
    @Override
    public Connection connectToDataBase() {
        String url = "jdbc:mysql://localhost:3306/account_customer_interview";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found. Please add JDBC driver.");
        } catch (SQLException e) {
            System.out.println("Can't connect to database. Check your connection.");
        } catch (Exception e) {
            System.out.println("Unknown exception." + e);
        }
        return null;
    }

    /**
     * disconnect to database and return nothing
     * Handle SQL Exception and Unknown exception
     */
    @Override
    public void disconnectToDataBase() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Can't disconnect from database.");
        } catch (Exception e) {
            System.out.println("Unknown exception." + e);
        }
    }
}