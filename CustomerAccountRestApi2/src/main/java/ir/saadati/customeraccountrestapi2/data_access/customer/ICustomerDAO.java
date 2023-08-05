package ir.saadati.customeraccountrestapi2.data_access.customer;

import ir.saadati.customeraccountrestapi2.service.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> getAllCustomers() throws Exception;

    Customer getCustomer(int customerId) throws Exception;

    Customer createCustomer(Customer customer) throws Exception;

    Customer updateCustomer(Customer customer) throws Exception;

    int deleteCustomer(Customer customer) throws Exception;
}