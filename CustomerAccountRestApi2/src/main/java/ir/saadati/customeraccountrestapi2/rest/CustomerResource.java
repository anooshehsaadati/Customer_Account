package ir.saadati.customeraccountrestapi2.rest;

import ir.saadati.customeraccountrestapi2.data_access.account.AccountDAO;
import ir.saadati.customeraccountrestapi2.data_access.customer.CustomerDAO;
import ir.saadati.customeraccountrestapi2.service.Account;
import ir.saadati.customeraccountrestapi2.service.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("customers")
public class CustomerResource {
    AccountDAO accountDAO = new AccountDAO();
    CustomerDAO customerDAO = new CustomerDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCustomers() throws Exception {
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entities not found!").build();
        } else {
            return Response.ok(customers, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCustomer(@PathParam("id") int id) throws Exception {
        Customer customer = customerDAO.getCustomer(id);
        if (customer.getCustomerId() == 0) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for Account ID: " + id).build();
        } else {
            return Response.ok(customer, MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createCustomer(Customer customer) throws Exception {
        Customer customerCreated = customerDAO.createCustomer(customer);
        if (customerCreated.getCustomerId() != 0) {
            return Response.ok(customerCreated, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No rows were affected in the create process.").build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCustomer(@PathParam("id") int id, Customer customer) throws Exception {
        Customer customerWithId = customerDAO.getCustomer(id);
        if (customerWithId.getCustomerId() == 0) {
            Customer customerCreated = customerDAO.createCustomer(customer);
            if (customerCreated.getCustomerId() != 0) {
                return Response.ok(customerCreated, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the create process.").build();
            }
        } else {
            customer.setCustomerId(customerWithId.getCustomerId());
            Customer customerUpdated = customerDAO.updateCustomer(customer);
            if (customerUpdated.getCustomerId() != 0) {
                return Response.ok(customerUpdated, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " exists. No rows were affected in the update process.").build();
            }
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCustomer(@PathParam("id") int id) throws Exception {
        int scenario = 2;
        // first scenario -- delete All Account of this Customer
        if (scenario == 1) {
            Customer customer = customerDAO.getCustomer(id);
            if (customer.getCustomerId() != 0) {
                List<Account> accounts = accountDAO.getAllAccounts();
                for (Account account : accounts) {
                    if (account.getCustomerId() == customer.getCustomerId()) {
                        int cnt = accountDAO.deleteAccount(account);
                        if (cnt == 0) {
                            return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.").build();
                        }
                    }
                }
                int count = customerDAO.deleteCustomer(customer);
                if (count == 0) {
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.").build();
                } else {
                    return Response.ok(customer, MediaType.APPLICATION_JSON).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("The requested resource could not be found. The specified row does not exist in the table.").build();
            }
        }
        // second scenario -- can't delete this customer when have any account
        else if (scenario == 2) {
            Customer customer = customerDAO.getCustomer(id);
            if (customer.getCustomerId() != 0) {
                List<Account> accounts = accountDAO.getAllAccounts();
                for (Account account : accounts) {
                    if (account.getCustomerId() == customer.getCustomerId()) {
                        return Response.status(Response.Status.CONFLICT).entity("Cannot delete the resource because it is referenced by a foreign key in another table. Please remove the references before attempting to delete.").build();
                    }
                }
                int count = customerDAO.deleteCustomer(customer);
                if (count == 0) {
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.").build();
                } else {
                    return Response.ok(customer, MediaType.APPLICATION_JSON).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("The requested resource could not be found. The specified row does not exist in the table.").build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("The requested resource could not be found.").build();
        }
    }
}
