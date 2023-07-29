package data_access.customer;

import data_access.connector.ConnectionToDataBase;
import service.Customer;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    @Override
    public List<Customer> getAllCustomers() throws Exception {
        // TODO: 7/29/2023 if customers table isn't exist --> error 
        String query = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<Customer>();
        ConnectionToDataBase connection;
        try {
            connection = new ConnectionToDataBase();
            // TODO: 7/29/2023 if connection error and can't to connect 
            Connection con = connection.connectToDataBase();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                // TODO: 7/29/2023 if any type of this make error 
                int customerId = rs.getInt("customerId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String idNumber = rs.getString("idNumber");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String birthDate = rs.getString("birthDate");
                int gender = rs.getInt("gender");
                String createdDate = rs.getString("createdDate");
                String updatedDate = rs.getString("updatedDate");
                customers.add(new Customer(customerId, firstName, lastName, idNumber, phoneNumber, address, email, birthDate, gender, createdDate, updatedDate));
            }
            st.close();
            // TODO: 7/29/2023 if disconnected false! 
            connection.disconnectToDataBase();
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it 
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomer(int customerId) throws Exception {
        // TODO: 7/29/2023 if customerId doesn't exist or table doesn't exist 
        String query = "SELECT * FROM customers WHERE customerId=" + customerId;
        ConnectionToDataBase connection;
        Customer customer = null;
        try {
            connection = new ConnectionToDataBase();
            // TODO: 7/29/2023 if connection error and can't to connect 
            Connection con = connection.connectToDataBase();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            // TODO: 7/29/2023 if any type of this make error 
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String idNumber = rs.getString("idNumber");
            String phoneNumber = rs.getString("phoneNumber");
            String address = rs.getString("address");
            String email = rs.getString("email");
            String birthDate = rs.getString("birthDate");
            int gender = rs.getInt("gender");
            String createdDate = rs.getString("createdDate");
            String updatedDate = rs.getString("updatedDate");
            customer = new Customer(customerId, firstName, lastName, idNumber, phoneNumber, address, email, birthDate, gender, createdDate, updatedDate);

            st.close();
            // TODO: 7/29/2023 if disconnected false! 
            connection.disconnectToDataBase();
        } catch (Exception e) {
            // TODO: 7/29/2023 My exception and handel it 
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void createCustomer(Customer customer) throws Exception {
        // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist 
        String query = "INSERT INTO customers(`firstName`, `lastName`, `idNumber`, `phoneNumber`, `address`, `email`, `birthDate`, `gender`, `createdDate`, `updatedDate`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        ConnectionToDataBase connection;
        try {
            // TODO: 7/29/2023 if connection error and can't to connect 
            connection = new ConnectionToDataBase();
            Connection con = connection.connectToDataBase();
            PreparedStatement st = con.prepareStatement(query);
            // TODO: 7/29/2023 if any type of this make error 
            st.setString(1, customer.getFirstName());
            st.setString(2, customer.getLastName());
            st.setString(3, customer.getIdNumber());
            st.setString(4, customer.getPhoneNumber());
            st.setString(5, customer.getAddress());
            st.setString(6, customer.getEmail());

            java.util.Date myDate = new java.util.Date(customer.getBirthDate());
            Date sqlDate = new Date(myDate.getTime());
            st.setDate(7, sqlDate);
            st.setInt(8, customer.getGender());

            Timestamp date = new Timestamp(new java.util.Date().getTime());
            st.setTimestamp(9, date);
            st.setTimestamp(10, date);
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
    public void updateCustomer(Customer customer) throws Exception {
        // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist 
        String query = "UPDATE customers SET firstName=?, lastName=?, idNumber=?, phoneNumber=?, address=?, email=?, birthDate=?, gender=?, updatedDate=? WHERE customerId=?";
        ConnectionToDataBase connection;
        try {
            // TODO: 7/29/2023 if connection error and can't to connect 
            connection = new ConnectionToDataBase();
            Connection con = connection.connectToDataBase();
            PreparedStatement st = con.prepareStatement(query);
            // TODO: 7/29/2023 if any type of this make error 
            st.setString(1, customer.getFirstName());
            st.setString(2, customer.getLastName());
            st.setString(3, customer.getIdNumber());
            st.setString(4, customer.getPhoneNumber());
            st.setString(5, customer.getAddress());
            st.setString(6, customer.getEmail());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(customer.getBirthDate());
            Date sqlDate = new Date(utilDate.getTime());
            st.setDate(7, sqlDate);
            st.setInt(8, customer.getGender());

            Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
            st.setTimestamp(9, timestamp);
            st.setInt(10, customer.getCustomerId());
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
    public void deleteCustomer(Customer customer) {
        // TODO: 7/29/2023 if any of this column not exist or wrong value or table does not exist 
        String query = "DELETE FROM customers WHERE customerId=?";
        ConnectionToDataBase connection;
        try {
            connection = new ConnectionToDataBase();
            // TODO: 7/29/2023 if connection error and can't to connect 
            Connection con = connection.connectToDataBase();
            PreparedStatement st = con.prepareStatement(query);
            // TODO: 7/29/2023 if any type of this make error 
            st.setInt(1, customer.getCustomerId());
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