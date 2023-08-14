package ir.saadati.customeraccountrestapi2.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public abstract class Resource {
    /**
     * this is get method and return all objects
     * return in format JSON
     *
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public abstract Response gets() throws Exception;

    /**
     * this is get method and return specific object with id
     * return in format JSON
     *
     * @param id specific id
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public abstract Response get(@PathParam("id") int id) throws Exception;

    /**
     * this is deleted method and delete specific object with id and return object if success delete
     * return in format JSON
     *
     * @param id specific id
     * @return Response of success/failure
     * @throws Exception connecting to database
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public abstract Response delete(@PathParam("id") int id) throws Exception;
}
