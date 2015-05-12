package eu.lpinto.petshelter.api.services;

import eu.lpinto.petshelter.api.dto.Animal;
import eu.lpinto.petshelter.api.dto.AnimalDTO;
import eu.lpinto.petshelter.api.dto.Error;
import eu.lpinto.petshelter.api.dto.Organization;
import eu.lpinto.petshelter.api.dto.OrganizationDTO;
import eu.lpinto.petshelter.api.dts.OrganizationsDTS;
import eu.lpinto.petshelter.persistence.facades.AnimalFacade;
import eu.lpinto.petshelter.persistence.facades.OrganizationFacade;
import eu.lpinto.petshelter.persistence.facades.UserFacade;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
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

    final Logger logger = LoggerFactory.getLogger(Organizations.class);

    @EJB
    private OrganizationFacade orgFacade;

    @EJB
    private UserFacade userFacade;

    @EJB
    private AnimalFacade animalsFacade;

    /*
     * CRUD
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@HeaderParam("userID") final Integer userID) {
        try {
            return Response.ok(OrganizationDTO.valueOf(userFacade.retrieve(userID).getOrganizations())).build();

        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Error(ex.getLocalizedMessage())).build();
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
        eu.lpinto.petshelter.persistence.entities.Organization result;

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
            return new OrganizationDTO(result);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userID") final Integer userID, final OrganizationDTO organization) {
        try {
            /* Create Organization */
            eu.lpinto.petshelter.persistence.entities.Organization newOrganization = organization.entity();
            newOrganization.setUsers(Arrays.asList(userFacade.retrieve(userID)));
            orgFacade.create(newOrganization);

            /* edit User */
            eu.lpinto.petshelter.persistence.entities.User user = userFacade.retrieve(userID);
            user.addOrg(newOrganization);
            userFacade.edit(user);
            return Response.ok(new OrganizationDTO(newOrganization)).build();

        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Error(ex.getLocalizedMessage())).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") final int organizationID, @HeaderParam("userID") final Integer userID, final OrganizationDTO organization) {
        if (organization.id != null && organizationID != organization.id) {
            throw new WebApplicationException("Cannot update organization", Response.Status.BAD_REQUEST);
        }

        organization.id = organizationID;

        eu.lpinto.petshelter.persistence.entities.Organization savedOrganization;

        try {
            savedOrganization = orgFacade.retrieve(organizationID);

            if (savedOrganization == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Error("Unknown organization id: " + organizationID)).build();
            }

            if (userFacade.retrieve(userID).getOrganization(organizationID) == null) {
                return Response.status(Response.Status.FORBIDDEN).entity(new Error("User not in the animals' organization.")).build();
            }

            eu.lpinto.petshelter.persistence.entities.Organization entity = organization.entity();
            orgFacade.edit(entity);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Error(ex.getLocalizedMessage())).build();
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
            return AnimalDTO.valueOf(orgFacade.retrieve(orgID).getAnimals());

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return new ArrayList<>(0);
        }
    }

    @GET
    @Path("{id}/animals/{animalID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAnimal(@HeaderParam("userID") final Integer userID, @PathParam("id") final int orgID, @PathParam("animalID") final int animalID) {
        try {
            /* Find Organizations */
            return Response.ok().entity(new AnimalDTO(userFacade.retrieve(userID).getOrganization(orgID).getAnimal(animalID))).build();

        } catch (RuntimeException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getLocalizedMessage()).build();
        }
    }

    @POST
    @Path("{id}/animals")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAnimal(@HeaderParam("userID") final Integer userID, @PathParam("id") final int orgID, AnimalDTO animal) {
        try {
            /* Find Organizations */
            eu.lpinto.petshelter.persistence.entities.Animal saveAnimal = animal.entity();
            saveAnimal.setOrganization(orgFacade.retrieve(orgID));
            animalsFacade.create(saveAnimal);

            return Response.ok().entity(new AnimalDTO(saveAnimal)).build();

        } catch (RuntimeException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getLocalizedMessage()).build();
        }
    }

    @PUT
    @Path("{orgID}/animals/{animalID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAnimal(@HeaderParam("userID") final Integer userID, @PathParam("orgID") final int orgID, @PathParam("animalID") final int animalID, AnimalDTO animal) {
        try {
            eu.lpinto.petshelter.persistence.entities.Organization savedOrg = orgFacade.retrieve(orgID);

            if (savedOrg == null) {
                return Response.status(Response.Status.NOT_FOUND).build(); // org doesn't exist
            }

            if (!savedOrg.hasUser(userID)) {
                return Response.status(Response.Status.FORBIDDEN).build(); // user not in org
            }

            if (!savedOrg.hasAnimal(animalID)) {
                return Response.status(Response.Status.FORBIDDEN).build(); // animal not in org
            }

            eu.lpinto.petshelter.persistence.entities.Animal saveAnimal = animal.entity();
            saveAnimal.setId(animalID);
            saveAnimal.setOrganization(savedOrg);
            animalsFacade.edit(saveAnimal);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (RuntimeException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getLocalizedMessage()).build();
        }
    }

}
