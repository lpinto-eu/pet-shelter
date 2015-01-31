package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.api.dts.AnimalsDTS;
import eu.lpinto.petshelter.entities.Animal;
import eu.lpinto.petshelter.entities.User;
import eu.lpinto.petshelter.facades.AnimalFacade;
import eu.lpinto.petshelter.facades.UserFacade;
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
@Path("animals")
public class Animals {

    @EJB
    private AnimalFacade animalsFacade;
    @EJB
    private UserFacade userFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> findAll(@HeaderParam("userID") final Integer userID) {
        try {
            /* get User */
            User user = userFacade.find(userID);

            /* retrieve animals */
            return animalsFacade.findAllByOrg(user.getOrganizationId());
        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return new ArrayList<>(0);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response export(@HeaderParam("userID") final Integer userID) {
        /* get User */
        User user = userFacade.find(userID);

        /* retrieve animals and build file */
        String content = AnimalsDTS.SINGLETON.tranform(animalsFacade.findAllByOrg(user.getOrganizationId()));
        byte[] b = content.getBytes(Charset.forName("UTF-8"));

        return Response.ok(b).header("Content-Disposition", "attachment; filename=Animals.csv").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Animal retrieve(@PathParam("id") final int id) {
        return animalsFacade.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Animal create(@HeaderParam("userID") final Integer userID, Animal animal) {
        User user = userFacade.find(userID);
        try {
            animal.setCreated(new GregorianCalendar());
            animal.setUpdated(new GregorianCalendar());
            if (animal.getOrganization() == 0) {
                animal.setOrganization(user.getOrganizationId());
            }
            animalsFacade.create(animal);
            return animal;
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @POST
    @Path("load")
    @Consumes(MediaType.APPLICATION_JSON)
    public void load(List<Animal> animals) {
        for (Animal animal : animals) {
            animal.setCreated(new GregorianCalendar());
            animal.setUpdated(new GregorianCalendar());
            animalsFacade.create(animal);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") final int id, Animal animal) {
        animal.setUpdated(new GregorianCalendar());
        animalsFacade.edit(animal);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") final int id) {
        animalsFacade.remove(animalsFacade.find(id));
    }
}
