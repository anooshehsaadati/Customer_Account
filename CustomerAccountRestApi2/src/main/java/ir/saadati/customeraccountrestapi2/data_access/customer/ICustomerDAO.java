package ir.saadati.customeraccountrestapi2.data_access.customer;

import ir.saadati.customeraccountrestapi2.service.Customer;

import java.util.List;

/**
 * Interface of Customer data access for CRUD operation with database
 *
 * @author write with Anushe Saadati
 */
public interface ICustomerDAO {
    /**
     * Select all customers from database and return as a list of objects
     *
     * @return List<Customer> list of customers
     * @throws Exception connecting to database
     */
    List<Customer> getAllCustomers() throws Exception;

    /**
     * Select specific customer from database and return as an object
     *
     * @param customerId specific id for searching specific customer
     * @return Customer object of specific customer
     * @throws Exception connecting to database
     */
    Customer getCustomer(int customerId) throws Exception;

    /**
     * Create specific customer and save it to database
     *
     * @param customer specific customer object
     * @return Customer object of specific customer
     * @throws Exception connecting to database
     */
    Customer createCustomer(Customer customer) throws Exception;

    /**
     * Update specific customer with specific id and save it to database
     *
     * @param customer specific customer object
     * @return Customer object of specific customer
     * @throws Exception connecting to database
     */
    Customer updateCustomer(Customer customer) throws Exception;

    /**
     * Delete specific customer with specific id from database
     *
     * @param customer specific customer object
     * @return int count of row effected
     * @throws Exception connecting to database
     */
    int deleteCustomer(Customer customer) throws Exception;
}