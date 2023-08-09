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
 * Customer Resource for connection between rest api and classes
 * in path /customers
 * support all CRUD method
 *
 * @author write with Anushe Saadati
 */
@Path("customers")
public class CustomerResource extends Resource {
    private static final Logger logger = LogManager.getLogger(CustomerResource.class);
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
     * this is get method and return all customers
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
        logger.info("Get all customers");
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.size() == 0) {
            logger.error("Entities not found!");
            return Response.status(Response.Status.NOT_FOUND).entity("Entities not found!").build();
        } else {
            logger.info("Finish get all customers");
            return Response.ok(customers, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * this is get method and return specific customer with id
     * return in format JSON
     *
     * @param id specific id of customer
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response get(@PathParam("id") int id) throws Exception {
        logger.info("Get customer with id " + id);
        Customer customer = customerDAO.getCustomer(id);
        if (customer.getCustomerId() == 0) {
            logger.error("Entity not found for Customer ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for Customer ID: " + id).build();
        } else {
            logger.info("Finish customer with id " + id);
            return Response.ok(customer, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * this is post method and create specific customer and return object of customer if success creation
     * return in format JSON
     *
     * @param object specific customer
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response create(Object object) throws Exception {
        logger.info("Create object");
        if (object instanceof Customer) {
            logger.info("Create customer object");
            Customer customer = (Customer) object;
            Customer customerCreated = customerDAO.createCustomer(customer);
            if (customerCreated.getCustomerId() != 0) {
                logger.info("Finish create customer");
                return Response.ok(customerCreated, MediaType.APPLICATION_JSON).build();
            } else {
                logger.error("No rows were affected in the create process.");
                return Response.status(Response.Status.NOT_FOUND).entity("No rows were affected in the create process.").build();
            }
        } else {
            logger.error("Wrong input type");
            return Response.status(Response.Status.BAD_REQUEST).entity("Wrong input type").build();
        }
    }

    /**
     * this is put method and update specific customer with id and return object of customer if success update
     * return in format JSON
     *
     * @param object specific customer
     * @param id     specific customer id
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam("id") int id, Object object) throws Exception {
        logger.info("Update object");
        if (object instanceof Customer) {
            logger.info("Update customer object");
            Customer customer = (Customer) object;
            Customer customerWithId = customerDAO.getCustomer(id);
            if (customerWithId.getCustomerId() == 0) {
                Customer customerCreated = customerDAO.createCustomer(customer);
                if (customerCreated.getCustomerId() != 0) {
                    logger.info("Finish create customer because there is no record for this id " + id + " id for new customer is " + customerCreated.getCustomerId());
                    return Response.ok(customerCreated, MediaType.APPLICATION_JSON).build();
                } else {
                    logger.error("The requested resource with ID " + id + " could not be found. No rows were affected in the create process.");
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the create process.").build();
                }
            } else {
                customer.setCustomerId(customerWithId.getCustomerId());
                Customer customerUpdated = customerDAO.updateCustomer(customer);
                if (customerUpdated.getCustomerId() != 0) {
                    logger.info("Finish update customer with id " + id);
                    return Response.ok(customerUpdated, MediaType.APPLICATION_JSON).build();
                } else {
                    logger.error("The requested resource with ID " + id + " exists. No rows were affected in the update process.");
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " exists. No rows were affected in the update process.").build();
                }
            }
        } else {
            logger.error("Wrong input type");
            return Response.status(Response.Status.BAD_REQUEST).entity("Wrong input type").build();
        }
    }

    /**
     * this is deleted method and delete specific customer with id and return object of customer if success delete
     * return in format JSON
     * this method has 2 scenario
     * scenario1: delete All Account of this Customer
     * scenario2: can't delete this customer when have any account
     *
     * @param id specific customer id
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response delete(@PathParam("id") int id) throws Exception {
        logger.info("Delete customer with id " + id);
        int scenario = 2;
        logger.info("Delete customer with scenario number " + scenario);
        // first scenario -- delete All Account of this Customer
        if (scenario == 1) {
            logger.info("Start customer object with scenario number " + scenario);
            Customer customer = customerDAO.getCustomer(id);
            if (customer.getCustomerId() != 0) {
                List<Account> accounts = accountDAO.getAllAccounts();
                for (Account account : accounts) {
                    if (account.getCustomerId() == customer.getCustomerId()) {
                        int cnt = accountDAO.deleteAccount(account);
                        if (cnt == 0) {
                            logger.error("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.");
                            return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.").build();
                        }
                    }
                }
                int count = customerDAO.deleteCustomer(customer);
                if (count == 0) {
                    logger.error("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.");
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.").build();
                } else {
                    logger.info("Finish delete customer");
                    return Response.ok(customer, MediaType.APPLICATION_JSON).build();
                }
            } else {
                logger.error("The requested resource could not be found. The specified row does not exist in the table.");
                return Response.status(Response.Status.NOT_FOUND).entity("The requested resource could not be found. The specified row does not exist in the table.").build();
            }
        }
        // second scenario -- can't delete this customer when have any account
        else if (scenario == 2) {
            logger.info("Start delete customer with scenario number " + scenario);
            Customer customer = customerDAO.getCustomer(id);
            if (customer.getCustomerId() != 0) {
                List<Account> accounts = accountDAO.getAllAccounts();
                for (Account account : accounts) {
                    if (account.getCustomerId() == customer.getCustomerId()) {
                        logger.error("Cannot delete the resource because it is referenced by a foreign key in another table. Please remove the references before attempting to delete.");
                        return Response.status(Response.Status.CONFLICT).entity("Cannot delete the resource because it is referenced by a foreign key in another table. Please remove the references before attempting to delete.").build();
                    }
                }
                int count = customerDAO.deleteCustomer(customer);
                if (count == 0) {
                    logger.error("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.");
                    return Response.status(Response.Status.NOT_FOUND).entity("The requested resource with ID " + id + " could not be found. No rows were affected in the deletion process.").build();
                } else {
                    logger.info("Finish delete customer");
                    return Response.ok(customer, MediaType.APPLICATION_JSON).build();
                }
            } else {
                logger.error("The requested resource could not be found. The specified row does not exist in the table.");
                return Response.status(Response.Status.NOT_FOUND).entity("The requested resource could not be found. The specified row does not exist in the table.").build();
            }
        } else {
            logger.error("The requested resource could not be found.");
            return Response.status(Response.Status.BAD_REQUEST).entity("The requested resource could not be found.").build();
        }
    }
}
