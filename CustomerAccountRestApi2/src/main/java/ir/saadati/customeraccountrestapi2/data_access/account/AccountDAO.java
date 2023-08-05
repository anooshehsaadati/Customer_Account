package ir.saadati.customeraccountrestapi2.data_access.account;

import ir.saadati.customeraccountrestapi2.data_access.connector.ConnectionToDataBase;
import ir.saadati.customeraccountrestapi2.service.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccountDAO {
    ConnectionToDataBase connection = new ConnectionToDataBase();
    Connection con = null;

    @Override
    public List<Account> getAllAccounts() throws Exception {
        // TODO: 7/29/2023 if accounts table isn't exist --> error
        String query = "SELECT * FROM accounts";
        List<Account> accounts = new ArrayList<>();
        try {
            // TODO: 7/29/2023 if connection error and can't to connect
            if (con == null) {
                con = connection.connectToDataBase();
            }
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
            con = null;
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
        Account account = null;
        try {
            // TODO: 7/29/2023 if connection error and can't to connect
            if (con == null) {
                con = connection.connectToDataBase();
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                // TODO: 7/29/2023 if any type of this make error
                int customerId = rs.getInt("customerId");
                String accountNumber = rs.getString("accountNumber");
                String createdDate = rs.getString("createdDate");
                String updatedDate = rs.getString("updatedDate");
                account = new Account(accountId, customerId, accountNumber, createdDate, updatedDate);
            } else {
                account = new Account();
            }
            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
            con = null;
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account createAccount(Account account) throws Exception {
        PreparedStatement st;
        Account accountCreated = new Account();
        String queryCheck = "SELECT * FROM accounts WHERE accountNumber=? AND customerId=?";
        try {
            // TODO: 7/29/2023 if connection error and can't to connect
            if (con == null) {
                con = connection.connectToDataBase();
            }
            st = con.prepareStatement(queryCheck);
            st.setString(1, account.getAccountNumber());
            st.setInt(2, account.getCustomerId());
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist
                String query = "INSERT INTO accounts(`customerId`, `accountNumber`, `createdDate`, `updatedDate`) VALUES (?,?,?,?)";
                st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                // TODO: 7/29/2023 if any type of this make error
                st.setInt(1, account.getCustomerId());
                st.setString(2, account.getAccountNumber());

                Timestamp date = new Timestamp(new java.util.Date().getTime());
                st.setTimestamp(3, date);
                st.setTimestamp(4, date);
                int affectedRows = st.executeUpdate();

                if (affectedRows > 0) {
                    ResultSet generatedKeys = st.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int accountId = generatedKeys.getInt(1);
                        accountCreated = this.getAccount(accountId);
                    }
                }
            }
            else {
                int accountId = rs.getInt("accountId");
                accountCreated = this.getAccount(accountId);
            }
            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
            con = null;
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
        return accountCreated;
    }

    @Override
    public Account updateAccount(Account account) throws Exception {
        PreparedStatement st;
        Account accountCreated = new Account();
        String queryCheck = "SELECT * FROM accounts WHERE accountNumber=? AND customerId=?";
        try {
            // TODO: 7/29/2023 if connection error and can't to connect
            if (con == null) {
                con = connection.connectToDataBase();
            }
            st = con.prepareStatement(queryCheck);
            st.setString(1, account.getAccountNumber());
            st.setInt(2, account.getCustomerId());
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist
                String query = "UPDATE accounts SET customerId=?, accountNumber=?, updatedDate=? WHERE accountId=?";
                st = con.prepareStatement(query);
                // TODO: 7/29/2023 if any type of this make error
                st.setInt(1, account.getCustomerId());
                st.setString(2, account.getAccountNumber());

                Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
                st.setTimestamp(3, timestamp);
                st.setInt(4, account.getAccountId());
                int affectedRows = st.executeUpdate();

                if (affectedRows > 0) {
                    accountCreated = this.getAccount(account.getAccountId());
                }
            }
            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
            con = null;
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
        return accountCreated;
    }

    @Override
    public int deleteAccount(Account account) throws Exception {
        // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist
        String query = "DELETE FROM accounts WHERE accountId=?";
        int count = 0;
        try {
            // TODO: 7/29/2023 if connection error and can't to connect
            if (con == null) {
                con = connection.connectToDataBase();
            }
            PreparedStatement st = con.prepareStatement(query);
            // TODO: 7/29/2023 if any type of this make error
            st.setInt(1, account.getAccountId());
            count = st.executeUpdate();
            st.close();
            // TODO: 7/29/2023 if disconnected false!
            connection.disconnectToDataBase();
            con = null;
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it
            e.printStackTrace();
        }
        return count;
    }
}