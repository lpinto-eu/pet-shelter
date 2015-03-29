package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.api.dts.OrganizationsDTS;
import eu.lpinto.petshelter.entities.Animal;
import eu.lpinto.petshelter.entities.Organization;
import eu.lpinto.petshelter.facades.AnimalFacade;
import eu.lpinto.petshelter.facades.OrganizationFacade;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path("organizations")
public class Organizations {

    @EJB
    private OrganizationFacade orgFacade;

    @EJB
    private AnimalFacade animalFacade;

    /*
     CRUD
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Organization> findAll(@HeaderParam("userID") final Integer actionUserID) {
        try {
            /* Find Organizations */
            return orgFacade.findAll(actionUserID);

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return new ArrayList<>(0);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response export(@HeaderParam("userID") final Integer actionUserID) {
        /* Find organizations and Build File */
        String content = OrganizationsDTS.SINGLETON.transform(orgFacade.findAll(actionUserID));
        byte[] b = content.getBytes(Charset.forName("UTF-8"));

        return Response.ok(b).header("Content-Disposition", "attachment; filename=Organizations.csv").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Organization retrieve(@HeaderParam("userID") final Integer actionUserID,
            @PathParam("id") final int id) {
        return orgFacade.retrieve(actionUserID, id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Organization create(@HeaderParam("userID") final Integer actionUserID,
            Organization org) {
        try {
            orgFacade.create(actionUserID, org);
            return org;

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @POST
    @Path("load")
    @Consumes(MediaType.APPLICATION_JSON)
    public void load(List<Organization> orgs) {
        for (Organization animal : orgs) {
            animal.setCreated(new GregorianCalendar());
            animal.setUpdated(new GregorianCalendar());
            orgFacade.create(animal);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") final int orgID,
            @HeaderParam("userID") final Integer userID,
            Organization org) {

        org.setId(orgID);
        orgFacade.update(userID, org);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") final int orgID) {
        orgFacade.remove(orgFacade.retrieve(orgID));
    }

    /*
     Users
     */
    @POST
    @Path("{id}/users/{targetUserID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void associateUser(@HeaderParam("userID") final Integer actionUserID,
            @PathParam("id") final int id,
            @PathParam("targetUserID") final int targetUserID) {
        try {
            orgFacade.associateUser(actionUserID, id, targetUserID);

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @DELETE
    @Path("{id}/users/{targetUserID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void dissociateUser(@HeaderParam("userID") final Integer actionUserID,
            @PathParam("id") final int id,
            @PathParam("targetUserID") final int targetUserID) {
        try {
            orgFacade.dissociateUser(actionUserID, id, targetUserID);

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    /*
     Animals
     */
    @GET
    @Path("{id}/animals")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> findAllAnimals(@HeaderParam("userID") final Integer actionUserID,
            @PathParam("id") final int orgID) {
        try {
            /* Find Organizations */
            return animalFacade.findAll(actionUserID, orgID);

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return new ArrayList<>(0);
        }
    }

    @GET
    @Path("{id}/animals/{animalID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal retrieveAnimal(@HeaderParam("userID") final Integer actionUserID,
            @PathParam("id") final int orgID,
            @PathParam("animalID") final int animalID) {
        try {
            /* Find Organizations */
            return animalFacade.retrieve(actionUserID, orgID, animalID);

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return null;
        }
    }

    @POST
    @Path("{id}/animals")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal createAnimal(@HeaderParam("userID") final Integer actionUserID,
            @PathParam("id") final int orgID,
            Animal animal) {
        try {
            /* Find Organizations */
            return animalFacade.create(actionUserID, orgID, animal);

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return null;
        }
    }

}
