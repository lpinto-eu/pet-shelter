/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.entities.User;
import eu.lpinto.petshelter.facades.UserFacade;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public User create(final User user) {
        try {
            user.setCreated(new GregorianCalendar());
            user.setUpdated(new GregorianCalendar());
            if (user.getOrganizationId() == 0) {
                user.setOrganizationId(1);
            }
            usersFacade.create(user);
            return user;
        }
        catch (RuntimeException ex) {
            throw ex;
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
