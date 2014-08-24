/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.api.util.Digest;
import eu.lpinto.petshelter.entities.User;
import eu.lpinto.petshelter.facades.UserFacade;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Singleton
@Path("users")
public class Users {

    @EJB
    private UserFacade usersFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll() {
        return usersFacade.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User retrieve(@PathParam("id") final int id) {
        return usersFacade.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final User user) {
        try {
            user.setCreated(new GregorianCalendar());
            user.setUpdated(new GregorianCalendar());

            if (user.getOrganizationId() == 0) {
                user.setOrganizationId(1);
            }

            user.setPassword(Digest.getSHA(user.getPassword(), null));

            usersFacade.create(user);
            return Response.ok(user).build();

        } catch (RuntimeException ex) {
            if ((ex.getCause() != null)
                && (ex.getCause().getCause() != null)
                && (ex.getCause().getCause() instanceof ConstraintViolationException)) {
                ConstraintViolationException ex1 = (ConstraintViolationException) ex.getCause().getCause();

                if (ex1.getConstraintViolations().toArray() != null
                    && ex1.getConstraintViolations().toArray().length > 0
                    && ex1.getConstraintViolations().toArray()[0] instanceof ConstraintViolationException) {
                    System.out.println(ex1.getConstraintViolations().toArray()[0]);
                }

            } else {
                System.out.println("Exception: " + ex.getLocalizedMessage());
            }
            return Response.serverError().entity("Cannot create user. Please try again later.").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") final int id, final User user) {
        user.setUpdated(new GregorianCalendar());
        usersFacade.edit(user);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") final int id) {
        usersFacade.remove(usersFacade.find(id));
    }
}
