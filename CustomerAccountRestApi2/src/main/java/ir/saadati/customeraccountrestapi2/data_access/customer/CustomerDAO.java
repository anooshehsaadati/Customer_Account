package ir.saadati.customeraccountrestapi2.data_access.customer;

import ir.saadati.customeraccountrestapi2.data_access.connector.ConnectionToDataBase;
import ir.saadati.customeraccountrestapi2.service.Customer;
import ir.saadati.customeraccountrestapi2.service.exception_handling.ExistingTableException;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of Customer data access for CRUD operation with database implements from ICustomerDAO
 *
 * @author write with Anushe Saadati
 */
public class CustomerDAO implements ICustomerDAO {
    /**
     * attribute connection that object of class ConnectionToDataBase
     * this is for connecting to database
     */
    ConnectionToDataBase connection = new ConnectionToDataBase();

    /**
     * attribute con that object of class Connection
     * this is for saving connection and use for Statement
     */
    Connection con = null;

    /**
     * Select all customers from database and return as a list of objects
     *
     * @return List<Customer> list of customers
     * @throws Exception connecting to database
     */
    @Override
    public List<Customer> getAllCustomers() throws Exception {
        String query = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<Customer>();
        try {
            if (con == null) {
                con = connection.connectToDataBase();
            }
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, "customers", new String[]{"TABLE"});
            if (res.next()) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
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
                connection.disconnectToDataBase();
                con = null;
            } else {
                connection.disconnectToDataBase();
                con = null;
                throw new ExistingTableException("customers");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Select specific customer from database and return as an object
     *
     * @param customerId specific id for searching specific customer
     * @return Customer object of specific customer
     * @throws Exception connecting to database
     */
    @Override
    public Customer getCustomer(int customerId) throws Exception {
        String query = "SELECT * FROM customers WHERE customerId=" + customerId;
        Customer customer = null;
        try {
            if (con == null) {
                con = connection.connectToDataBase();
            }
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, "customers", new String[]{"TABLE"});
            if (res.next()) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
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
                } else {
                    customer = new Customer();
                }
                st.close();
                connection.disconnectToDataBase();
                con = null;
            } else {
                connection.disconnectToDataBase();
                con = null;
                throw new ExistingTableException("customers");
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Syntax error on query. please check it." + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    /**
     * Create specific customer and save it to database
     *
     * @param customer specific customer object
     * @return Customer object of specific customer
     * @throws Exception connecting to database
     */
    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        PreparedStatement st;
        Customer customerCreated = new Customer();
        String queryCheck = "SELECT * FROM customers WHERE idNumber=?";
        try {
            if (con == null) {
                con = connection.connectToDataBase();
            }
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, "customers", new String[]{"TABLE"});
            if (res.next()) {
                st = con.prepareStatement(queryCheck);
                st.setString(1, customer.getIdNumber());
                ResultSet rs = st.executeQuery();
                if (!rs.next()) {
                    String query = "INSERT INTO customers(`firstName`, `lastName`, `idNumber`, `phoneNumber`, `address`, `email`, `birthDate`, `gender`, `createdDate`, `updatedDate`) VALUES (?,?,?,?,?,?,?,?,?,?)";
                    st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    st.setString(1, customer.getFirstName());
                    st.setString(2, customer.getLastName());
                    st.setString(3, customer.getIdNumber());
                    st.setString(4, customer.getPhoneNumber());
                    st.setString(5, customer.getAddress());
                    st.setString(6, customer.getEmail());

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date myDate = df.parse(customer.getBirthDate());
                    Date sqlDate = new Date(myDate.getTime());
                    st.setDate(7, sqlDate);
                    st.setInt(8, customer.getGender());

                    Timestamp date = new Timestamp(new java.util.Date().getTime());
                    st.setTimestamp(9, date);
                    st.setTimestamp(10, date);
                    int affectedRows = st.executeUpdate();

                    if (affectedRows > 0) {
                        ResultSet generatedKeys = st.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int customerId = generatedKeys.getInt(1);
                            customerCreated = this.getCustomer(customerId);
                        }
                    }
                } else {
                    int customerId = rs.getInt("customerId");
                    customerCreated = this.getCustomer(customerId);
                }
                st.close();
                connection.disconnectToDataBase();
                con = null;
            } else {
                connection.disconnectToDataBase();
                con = null;
                throw new ExistingTableException("customers");
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Syntax error on query. please check it." + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerCreated;
    }

    /**
     * Update specific customer with specific id and save it to database
     *
     * @param customer specific customer object
     * @return Customer object of specific customer
     * @throws Exception connecting to database
     */
    @Override
    public Customer updateCustomer(Customer customer) throws Exception {
        PreparedStatement st;
        Customer customerCreated = new Customer();
        String queryCheck = "SELECT * FROM customers WHERE idNumber=? AND customerId=?";
        try {
            if (con == null) {
                con = connection.connectToDataBase();
            }
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, "customers", new String[]{"TABLE"});
            if (res.next()) {
                st = con.prepareStatement(queryCheck);
                st.setString(1, customer.getIdNumber());
                st.setInt(2, customer.getCustomerId());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String query = "UPDATE customers SET firstName=?, lastName=?, idNumber=?, phoneNumber=?, address=?, email=?, birthDate=?, gender=?, updatedDate=? WHERE customerId=?";
                    st = con.prepareStatement(query);
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
                    int affectedRows = st.executeUpdate();

                    if (affectedRows > 0) {
                        customerCreated = this.getCustomer(customer.getCustomerId());
                    }
                }
                st.close();
                connection.disconnectToDataBase();
                con = null;
            } else {
                connection.disconnectToDataBase();
                con = null;
                throw new ExistingTableException("customers");
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Syntax error on query. please check it." + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerCreated;
    }

    /**
     * Delete specific customer with specific id from database
     *
     * @param customer specific customer object
     * @return int count of row effected
     * @throws Exception connecting to database
     */
    @Override
    public int deleteCustomer(Customer customer) throws Exception {
        String query = "DELETE FROM customers WHERE customerId=?";
        int count = 0;
        try {
            if (con == null) {
                con = connection.connectToDataBase();
            }
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, "customers", new String[]{"TABLE"});
            if (res.next()) {
                PreparedStatement st = con.prepareStatement(query);
                st.setInt(1, customer.getCustomerId());
                count = st.executeUpdate();
                st.close();
                connection.disconnectToDataBase();
                con = null;
            } else {
                connection.disconnectToDataBase();
                con = null;
                throw new ExistingTableException("customers");
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Syntax error on query. please check it." + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}