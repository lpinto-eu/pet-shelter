package eu.lpinto.petshelter.api.services;

import eu.lpinto.petshelter.api.dto.Animal;
import eu.lpinto.petshelter.api.dto.AnimalDTO;
import eu.lpinto.petshelter.api.dto.Error;
import eu.lpinto.petshelter.persistence.facades.AnimalFacade;
import eu.lpinto.petshelter.persistence.facades.OrganizationFacade;
import eu.lpinto.petshelter.persistence.facades.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path("animals")
public class Animals {

    final Logger logger = LoggerFactory.getLogger(Animals.class);

    @EJB
    private AnimalFacade animalsFacade;
    @EJB
    private UserFacade usersFacade;
    @EJB
    private OrganizationFacade organizationsFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> findAll(@HeaderParam("userID") final Integer userID) {
        try {
            return AnimalDTO.valueOf(animalsFacade.findAll());

        } catch (WebApplicationException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve(@HeaderParam("userID") final Integer userID, @PathParam("id") final int id) {
        try {
            eu.lpinto.petshelter.persistence.entities.Animal aniimal = animalsFacade.retrieve(id);

            if (aniimal == null) {
                return Response.status(Response.Status.NOT_IMPLEMENTED).build();
            }

            if (!aniimal.getOrganization().hasUser(userID)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            return Response.ok(new AnimalDTO(aniimal)).build();

        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getLocalizedMessage()).build();
        }
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response create(@HeaderParam("userID") final Integer userID, eu.lpinto.petshelter.persistence.entities.Animal animal) {
//        try {
//            if (!usersFacade.retrieve(userID).hasOrganization(animal.getOrganization().getId())) {
//                return Response.status(Response.Status.CONFLICT).entity("Please create an organization first.").build();
//            }
//
//            animalsFacade.create(animal);
//
//            return Response.ok(new AnimalDTO(animal)).build();
//
//        } catch (WebApplicationException ex) {
//            throw ex;
//        } catch (RuntimeException ex) {
//            logger.debug(ex.getLocalizedMessage(), ex);
//            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response update(@HeaderParam("userID") final Integer userID, @PathParam("id") final int id, AnimalDTO animal) {
//        try {
//
//            if (animal.organization == null || organizationsFacade.retrieve(animal.organization) == null || !organizationsFacade.retrieve(animal.organization).hasUser(userID)) {
//                Response.status(Response.Status.CONFLICT).entity("User is not part of same organization as the animal.").build();
//            }
//
//            animalsFacade.edit(animal.entity());
//
//            return Response.status(Response.Status.NO_CONTENT).build();
//
//        } catch (WebApplicationException ex) {
//            throw ex;
//        } catch (RuntimeException ex) {
//            logger.debug(ex.getLocalizedMessage(), ex);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@HeaderParam("userID") final Integer userID, @PathParam("id") final int id) {
        try {
            eu.lpinto.petshelter.persistence.entities.Animal savedAnimal = animalsFacade.retrieve(id);

            if (savedAnimal == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(new Error("Unknown Animal id.")).build();
            }

            if (!savedAnimal.getOrganization().hasUser(userID)) {
                return Response.status(Response.Status.FORBIDDEN).entity(new Error("User not in the animals' organization.")).build();
            }

            animalsFacade.remove(savedAnimal);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (WebApplicationException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Error(ex.getLocalizedMessage())).build();
        }
    }
}
