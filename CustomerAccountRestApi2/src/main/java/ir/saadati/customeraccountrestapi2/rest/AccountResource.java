package ir.saadati.customeraccountrestapi2.rest;

import ir.saadati.customeraccountrestapi2.data_access.account.AccountDAO;
import ir.saadati.customeraccountrestapi2.data_access.customer.CustomerDAO;
import ir.saadati.customeraccountrestapi2.service.Account;
import ir.saadati.customeraccountrestapi2.service.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("accounts")
public class AccountResource {
    AccountDAO accountDAO = new AccountDAO();
    CustomerDAO customerDAO = new CustomerDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAccounts() throws Exception {
        List<Account> customers = accountDAO.getAllAccounts();
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
    public Response getAccount(@PathParam("id") int id) throws Exception {
        Account account = accountDAO.getAccount(id);
        if (account.getAccountId() == 0) {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for Account ID: " + id).build();
        } else {
            return Response.ok(account, MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createAccount(Account account) throws Exception {
        Customer customerWithId = customerDAO.getCustomer(account.getCustomerId());
        if (customerWithId.getCustomerId() != 0) {
            Account accountCreated = accountDAO.createAccount(account);
            if (accountCreated.getAccountId() != 0) {
                return Response.ok(accountCreated, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No rows were affected in the create process.").build();
            }
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("The provided foreign key value does not match any existing entry in the main table. Please provide a valid foreign key value.").build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAccount(@PathParam("id") int id, Account account) throws Exception {
        Customer customerWithId = customerDAO.getCustomer(account.getCustomerId());
        if (customerWithId.getCustomerId() != 0) {
            Account accountWithId = accountDAO.getAccount(id);
            if (accountWithId.getAccountId() == 0) {
                Account accountCreated = accountDAO.createAccount(account);
                if (accountCreated.getAccountId() != 0) {
                    return Response.ok(accountCreated, MediaType.APPLICATION_JSON).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the create process.").build();
                }
            } else {
                account.setAccountId(accountWithId.getAccountId());
                Account accountUpdated = accountDAO.updateAccount(account);
                if (accountUpdated.getAccountId() != 0) {
                    return Response.ok(accountUpdated, MediaType.APPLICATION_JSON).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " exists. No rows were affected in the update process.").build();
                }
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("The provided foreign key value does not match any existing entry in the main table. Please provide a valid foreign key value.").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteAccount(@PathParam("id") int id) throws Exception {
        Account account = accountDAO.getAccount(id);
        if (account.getAccountId() != 0) {
            int count = accountDAO.deleteAccount(account);
            if (count == 0) {
                return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.").build();
            } else {
                return Response.ok(account, MediaType.APPLICATION_JSON).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("The requested resource could not be found. The specified row does not exist in the table.").build();
        }
    }
}
