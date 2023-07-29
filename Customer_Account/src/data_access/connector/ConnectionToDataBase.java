package data_access.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToDataBase implements IConnectionToDataBase {
    private Connection con;

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

    @Override
    public void disconnectToDataBase() throws Exception {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
