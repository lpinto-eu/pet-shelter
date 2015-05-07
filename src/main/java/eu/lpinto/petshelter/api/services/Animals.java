package eu.lpinto.petshelter.api.services;

import eu.lpinto.petshelter.api.dto.Animal;
import eu.lpinto.petshelter.api.dto.AnimalDTO;
import eu.lpinto.petshelter.api.dts.AnimalsDTS;
import eu.lpinto.petshelter.persistence.entities.Organization;
import eu.lpinto.petshelter.persistence.entities.User;
import eu.lpinto.petshelter.persistence.facades.AnimalFacade;
import eu.lpinto.petshelter.persistence.facades.UserFacade;
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
@Path("animals")
public class Animals {

    final Logger logger = LoggerFactory.getLogger(Animals.class);

    @EJB
    private AnimalFacade animalsFacade;
    @EJB
    private UserFacade usersFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> findAll(@HeaderParam("userID") final Integer userID) {
        try {
            if (getOrganization(userID) == null || getOrganization(userID).getAnimals() == null) {
                return new ArrayList<>(0);
            } else {
                return AnimalDTO.fromList(getOrganization(userID).getAnimals());
            }

        } catch (WebApplicationException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response export(@HeaderParam("userID") final Integer userID, @PathParam("orgID") final int orgID) {
        /* retrieve animals and build file */
        String content = AnimalsDTS.SINGLETON.transform(getOrganization(userID).getAnimals());
        byte[] b = content.getBytes(Charset.forName("UTF-8"));

        return Response.ok(b).header("Content-Disposition", "attachment; filename=Animals.csv").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal retrieve(@HeaderParam("userID") final Integer userID, @PathParam("id") final int id) {
        try {
            return new AnimalDTO(getOrganization(userID).getAnimal(id));

        } catch (WebApplicationException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userID") final Integer userID, eu.lpinto.petshelter.persistence.entities.Animal animal) {
        try {
            if (getOrganization(userID) == null) {
                return Response.status(Response.Status.CONFLICT).entity("Please create an organization first.").build();
            }

            animal.setOrganization(getOrganization(userID));
            animalsFacade.create(animal);

            return Response.ok(new AnimalDTO(animal)).build();

        } catch (WebApplicationException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("load")
    @Consumes(MediaType.APPLICATION_JSON)
    public void load(@HeaderParam("userID") final Integer userID, List<eu.lpinto.petshelter.persistence.entities.Animal> animals) {
        try {
            for (eu.lpinto.petshelter.persistence.entities.Animal animal : animals) {
                animal.setOrganization(getOrganization(userID));
                animalsFacade.create(animal);
            }

        } catch (WebApplicationException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userID") final Integer userID, @PathParam("id") final int id, eu.lpinto.petshelter.persistence.entities.Animal animal) {
        try {

            if (getOrganization(userID).getAnimal(animal.getId()) == null) {
                Response.status(Response.Status.CONFLICT).entity("User is not part of same organization as the animal.").build();
            }

            animal.setOrganization(getOrganization(userID));
            animalsFacade.edit(animal);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (WebApplicationException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@HeaderParam("userID") final Integer userID, @PathParam("id") final int id) {
        try {
            Organization organization = getOrganization(userID);

            if (organization.getAnimal(id) == null) {
                throw new WebApplicationException(Response.Status.CONFLICT);
            }

            animalsFacade.remove(animalsFacade.retrieve(id));

        } catch (WebApplicationException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            logger.debug(ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Helpers
     */
    private Organization getOrganization(final Integer userID) throws WebApplicationException {
        User user = usersFacade.retrieve(userID);

        if (user.getOrganizations() == null || user.getOrganizations().size() != 1) {
            return null;

        } else {
            return user.getOrganizations().get(0);
        }
    }
}
