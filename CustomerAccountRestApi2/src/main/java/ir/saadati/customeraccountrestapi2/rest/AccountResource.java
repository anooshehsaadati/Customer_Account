package ir.saadati.customeraccountrestapi2.rest;

import ir.saadati.customeraccountrestapi2.data_access.account.AccountDAO;
import ir.saadati.customeraccountrestapi2.data_access.customer.CustomerDAO;
import ir.saadati.customeraccountrestapi2.service.Account;
import ir.saadati.customeraccountrestapi2.service.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Account Resource for connection between rest api and classes
 * in path /accounts
 * support all CRUD method
 *
 * @author write with Anushe Saadati
 */
@Path("accounts")
public class AccountResource extends Resource {
    private static final Logger logger = LogManager.getLogger("AccountResource");
    /**
     * account data access object is object of AccountDAO
     * and connect to database for CRUD operations
     */
    AccountDAO accountDAO = new AccountDAO();

    /**
     * customer data access object is object of CustomerDAO
     * and connect to database for CRUD operations
     */
    CustomerDAO customerDAO = new CustomerDAO();

    /**
     * this is get method and return all accounts
     * return in format JSON
     *
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response gets() throws Exception {
        logger.info("Get all accounts");
        List<Account> accounts = accountDAO.getAllAccounts();
        if (accounts.size() == 0) {
            logger.error("Entities not found!");
            return Response.status(Response.Status.NOT_FOUND).entity("Entities not found!").build();
        } else {
            logger.info("Finish get all accounts");
            return Response.ok(accounts, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * this is get method and return specific account with id
     * return in format JSON
     *
     * @param id specific id of account
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response get(@PathParam("id") int id) throws Exception {
        logger.info("Get account with id " + id);
        Account account = accountDAO.getAccount(id);
        if (account.getAccountId() == 0) {
            logger.error("Entity not found for Account ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for Account ID: " + id).build();
        } else {
            logger.info("Finish account with id " + id);
            return Response.ok(account, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * this is post method and create specific account and return object of account if success creation
     * return in format JSON
     *
     * @param account specific account
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Account account) throws Exception {
        logger.info("Create account object");
        Customer customerWithId = customerDAO.getCustomer(account.getCustomerId());
        if (customerWithId.getCustomerId() != 0) {
            Account accountCreated = accountDAO.createAccount(account);
            if (accountCreated.getAccountId() != 0) {
                logger.info("Finish create account");
                return Response.ok(accountCreated, MediaType.APPLICATION_JSON).build();
            } else {
                logger.error("No rows were affected in the create process.");
                return Response.status(Response.Status.NOT_FOUND).entity("No rows were affected in the create process.").build();
            }
        } else {
            logger.error("The provided foreign key value does not match any existing entry in the main table. Please provide a valid foreign key value.");
            return Response.status(Response.Status.BAD_REQUEST).entity("The provided foreign key value does not match any existing entry in the main table. Please provide a valid foreign key value.").build();
        }
    }

    /**
     * this is put method and update specific account with id and return object of account if success update
     * return in format JSON
     *
     * @param account specific account
     * @param id      specific account id
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Account account) throws Exception {
        logger.info("Update account object");
        Customer customerWithId = customerDAO.getCustomer(account.getCustomerId());
        if (customerWithId.getCustomerId() != 0) {
            Account accountWithId = accountDAO.getAccount(id);
            if (accountWithId.getAccountId() == 0) {
                Account accountCreated = accountDAO.createAccount(account);
                if (accountCreated.getAccountId() != 0) {
                    logger.info("Finish create account because there is no record for this id " + id + " id for new account is " + accountCreated.getAccountId());
                    return Response.ok(accountCreated, MediaType.APPLICATION_JSON).build();
                } else {
                    logger.error("The requested resource with ID " + id + " could not be found. No rows were affected in the create process.");
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the create process.").build();
                }
            } else {
                account.setAccountId(accountWithId.getAccountId());
                Account accountUpdated = accountDAO.updateAccount(account);
                if (accountUpdated.getAccountId() != 0) {
                    logger.info("Finish update account with id " + accountUpdated.getAccountId());
                    return Response.ok(accountUpdated, MediaType.APPLICATION_JSON).build();
                } else {
                    logger.error("The requested resource exists. No rows were affected in the update process.");
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource exists. No rows were affected in the update process.").build();
                }
            }
        } else {
            logger.error("The provided foreign key value does not match any existing entry in the main table. Please provide a valid foreign key value.");
            return Response.status(Response.Status.BAD_REQUEST).entity("The provided foreign key value does not match any existing entry in the main table. Please provide a valid foreign key value.").build();
        }
    }

    /**
     * this is deleted method and delete specific account with id and return object of account if success delete
     * return in format JSON
     *
     * @param id specific account id
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response delete(@PathParam("id") int id) throws Exception {
        logger.info("Delete account with id " + id);
        Account account = accountDAO.getAccount(id);
        if (account.getAccountId() != 0) {
            int count = accountDAO.deleteAccount(account);
            if (count == 0) {
                logger.error("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.");
                return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.").build();
            } else {
                logger.info("Finish delete account");
                return Response.ok(account, MediaType.APPLICATION_JSON).build();
            }
        } else {
            logger.error("The requested resource could not be found. The specified row does not exist in the table.");
            return Response.status(Response.Status.NOT_FOUND).entity("The requested resource could not be found. The specified row does not exist in the table.").build();
        }
    }
}
