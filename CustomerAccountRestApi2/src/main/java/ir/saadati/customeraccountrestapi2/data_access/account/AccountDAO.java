package ir.saadati.customeraccountrestapi2.data_access.account;

import ir.saadati.customeraccountrestapi2.data_access.connector.ConnectionToDataBase;
import ir.saadati.customeraccountrestapi2.service.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccountDAO {
    @Override
    public List<Account> getAllAccounts() throws Exception {
        // TODO: 7/29/2023 if accounts table isn't exist --> error
        String query = "SELECT * FROM accounts";
        List<Account> accounts = new ArrayList<>();
        ConnectionToDataBase connection;
        try {
            connection = new ConnectionToDataBase();
            // TODO: 7/29/2023 if connection error and can't to connect
            Connection con = connection.connectToDataBase();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                // TODO: 7/29/2023 if any type of this make error
                int accountId = rs.getInt("accountId");
                int customerId = rs.getInt("customerId");
                String accountNumber = rs.getString("accountNumber");
                String createdDate = rs.getString("createdDate");
                String updatedDate = rs.getString("updatedDate");
                accounts.add(new Account(accountId, customerId, accountNumber, createdDate, updatedDate));
            }
            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account getAccount(int accountId) throws Exception {
        // TODO: 7/29/2023 if accountId doesn't exist or table doesn't exist
        String query = "SELECT * FROM accounts WHERE accountId=" + accountId;
        ConnectionToDataBase connection;
        Account account = null;
        try {
            connection = new ConnectionToDataBase();
            // TODO: 7/29/2023 if connection error and can't to connect
            Connection con = connection.connectToDataBase();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            // TODO: 7/29/2023 if any type of this make error
            int customerId = rs.getInt("customerId");
            String accountNumber = rs.getString("accountNumber");
            String createdDate = rs.getString("createdDate");
            String updatedDate = rs.getString("updatedDate");
            account = new Account(accountId, customerId, accountNumber, createdDate, updatedDate);

            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void createAccount(Account account) throws Exception {
        // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist
        String query = "INSERT INTO accounts(`customerId`, `accountNumber`, `createdDate`, `updatedDate`) VALUES (?,?,?,?)";
        ConnectionToDataBase connection;
        try {
            // TODO: 7/29/2023 if connection error and can't to connect
            connection = new ConnectionToDataBase();
            Connection con = connection.connectToDataBase();
            PreparedStatement st = con.prepareStatement(query);
            // TODO: 7/29/2023 if any type of this make error
            st.setInt(1, account.getCustomerId());
            st.setString(2, account.getAccountNumber());

            Timestamp date = new Timestamp(new java.util.Date().getTime());
            st.setTimestamp(3, date);
            st.setTimestamp(4, date);
            st.execute();

            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) throws Exception {
        // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist
        String query = "UPDATE accounts SET customerId=?, accountNumber=?, updatedDate=? WHERE accountId=?";
        ConnectionToDataBase connection;
        try {
            // TODO: 7/29/2023 if connection error and can't to connect
            connection = new ConnectionToDataBase();
            Connection con = connection.connectToDataBase();
            PreparedStatement st = con.prepareStatement(query);
            // TODO: 7/29/2023 if any type of this make error
            st.setInt(1, account.getCustomerId());
            st.setString(2, account.getAccountNumber());

            Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
            st.setTimestamp(3, timestamp);
            st.setInt(4, account.getAccountId());
            st.executeUpdate();
            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Account account) throws Exception {
        // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist
        String query = "DELETE FROM accounts WHERE accountId=?";
        ConnectionToDataBase connection;
        try {
            connection = new ConnectionToDataBase();
            // TODO: 7/29/2023 if connection error and can't to connect
            Connection con = connection.connectToDataBase();
            PreparedStatement st = con.prepareStatement(query);
            // TODO: 7/29/2023 if any type of this make error
            st.setInt(1, account.getAccountId());
            st.executeUpdate();
            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
    }
}
