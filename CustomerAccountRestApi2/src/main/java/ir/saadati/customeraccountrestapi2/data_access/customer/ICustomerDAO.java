package ir.saadati.customeraccountrestapi2.data_access.customer;

import ir.saadati.customeraccountrestapi2.service.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> getAllCustomers() throws Exception;

    Customer getCustomer(int customerId) throws Exception;

    void createCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    void deleteCustomer(Customer customer) throws Exception;
}