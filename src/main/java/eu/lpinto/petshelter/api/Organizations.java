package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.api.dts.OrganizationsDTS;
import eu.lpinto.petshelter.api.filters.TrafficLogger;
import eu.lpinto.petshelter.entities.Animal;
import eu.lpinto.petshelter.entities.Organization;
import eu.lpinto.petshelter.entities.User;
import eu.lpinto.petshelter.facades.OrganizationFacade;
import eu.lpinto.petshelter.facades.UserFacade;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Singleton
@Path("organizations")
public class Organizations {

    final Logger logger = LoggerFactory.getLogger(TrafficLogger.class);

    @EJB
    private OrganizationFacade orgFacade;

    @EJB
    private UserFacade userFacade;

    /*
     * CRUD
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Organization> findAll(@HeaderParam("userID") final Integer userID) {
        try {
            return userFacade.retrieve(userID).getOrganizations();

        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException("Cannot retrieve organizations",
                                              ex,
                                              Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response export(@HeaderParam("userID") final Integer userID) {
        /* Find organizations and Build File */
        String content = OrganizationsDTS.SINGLETON.transform(userFacade.retrieve(userID).getOrganizations());
        byte[] b = content.getBytes(Charset.forName("UTF-8"));

        return Response.ok(b).header("Content-Disposition", "attachment; filename=Organizations.csv").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Organization retrieve(@HeaderParam("userID") final Integer userID, @PathParam("id") final int organizationID) {
        Organization result;

        try {
            result = orgFacade.retrieve(organizationID);

        } catch (Exception ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException("Cannot retrieve organization", ex);
        }

        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        try {
            result = userFacade.retrieve(userID).getOrganization(organizationID);

        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException("Cannot retrieve organization", ex);
        }
        if (result == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);

        } else {
            return result;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userID") final Integer userID, final Organization organization) {
        try {
            /* Create Organization */
            orgFacade.create(organization);

            /* edit User */
            User user = userFacade.retrieve(userID);
            user.addOrg(organization);
            userFacade.edit(user);

            return Response.ok(organization).build();

        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException("Cannot create organization", ex);
        }
    }

    @POST
    @Path("load")
    @Consumes(MediaType.APPLICATION_JSON)
    public void load(List<Organization> organizations) {
        for (Organization animal : organizations) {
            orgFacade.create(animal);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") final int organizationID, @HeaderParam("userID") final Integer userID, final Organization organization) {
        if (organization.getId() != null && organizationID != organization.getId()) {
            throw new WebApplicationException("Cannot update organization", Response.Status.BAD_REQUEST);
        }

        organization.setId(organizationID);

        Organization result;

        try {
            result = orgFacade.retrieve(organizationID);

        } catch (Exception ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException("Cannot retrieve organization", ex);
        }

        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        try {
            result = userFacade.retrieve(userID).getOrganization(organizationID);

        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException("Cannot retrieve organization", ex);
        }
        if (result == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);

        } else {

            try {
                orgFacade.edit(organization);

            } catch (RuntimeException ex) {
                logger.debug(ex.getLocalizedMessage(), ex);
                throw new WebApplicationException("Cannot update organization", ex);
            }
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") final int organizationID) {
        try {
            orgFacade.remove(orgFacade.retrieve(organizationID));
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException("Cannot delete organization", ex);
        }
    }

    /*
     * Users
     */
    @POST
    @Path("{id}/users/{targetUserID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void associateUser(@HeaderParam("userID") final Integer userID, @PathParam("id") final int id, @PathParam("targetUserID") final int targetUserID) {
        try {
            orgFacade.associateUser(userID, id, targetUserID);

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @DELETE
    @Path("{id}/users/{targetUserID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void dissociateUser(@HeaderParam("userID") final Integer userID, @PathParam("id") final int id, @PathParam("targetUserID") final int targetUserID) {
        try {
            orgFacade.dissociateUser(userID, id, targetUserID);

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    /*
     * Animals
     */
    @GET
    @Path("{id}/animals")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> findAllAnimals(@HeaderParam("userID") final Integer userID, @PathParam("id") final int orgID) {
        try {
            /* Find Organizations */
            return orgFacade.retrieve(orgID).getAnimals();

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return new ArrayList<>(0);
        }
    }

    @GET
    @Path("{id}/animals/{animalID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal retrieveAnimal(@HeaderParam("userID") final Integer userID, @PathParam("id") final int orgID, @PathParam("animalID") final int animalID) {
        try {
            /* Find Organizations */
            return userFacade.retrieve(userID).getOrganization(orgID).getAnimal(animalID);

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return null;
        }
    }

    @POST
    @Path("{id}/animals")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal createAnimal(@HeaderParam("userID") final Integer userID, @PathParam("id") final int orgID, Animal animal) {
        try {
            /* Find Organizations */
            return new Animals().create(userID, animal).readEntity(Animal.class);

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return null;
        }
    }

}
