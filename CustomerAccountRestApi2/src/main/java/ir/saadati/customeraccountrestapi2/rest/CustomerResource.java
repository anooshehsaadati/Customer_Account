package ir.saadati.customeraccountrestapi2.rest;

import ir.saadati.customeraccountrestapi2.data_access.customer.CustomerDAO;
import ir.saadati.customeraccountrestapi2.service.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("customers")
public class CustomerResource {
    CustomerDAO customerDAO = new CustomerDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Customer> getCustomers() throws Exception {
        return customerDAO.getAllCustomers();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer getCustomer(@PathParam("id") int id) throws Exception {
        return customerDAO.getCustomer(id);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer createCustomer(Customer customer) throws Exception {
        System.out.println(customer);
        customerDAO.createCustomer(customer);
        return customer;
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer updateCustomer(@PathParam("id") int id, Customer customer) throws Exception {
        Customer customer1 = this.getCustomer(id);
        System.out.println(customer1);
        System.out.println(customer1.getCustomerId());
        System.out.println(customer);
        System.out.println(customer.getCustomerId());
        if (customer1.getCustomerId() == 0) {
            customerDAO.createCustomer(customer);
        } else {
            System.out.println("11");
            customer.setCustomerId(customer1.getCustomerId());
            customerDAO.updateCustomer(customer);
        }
        return customer;
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer deleteCustomer(@PathParam("id") int id) throws Exception {
        Customer customer = this.getCustomer(id);
        if (customer.getCustomerId() != 0) {
            customerDAO.deleteCustomer(customer);
        }
        return customer;
    }
}
