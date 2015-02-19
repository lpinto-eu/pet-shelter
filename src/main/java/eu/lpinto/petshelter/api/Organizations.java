package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.api.dts.OrganizationsDTS;
import eu.lpinto.petshelter.entities.Organization;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Organization> findAll() {
        try {
            /* Find Organizations */
            return orgFacade.findAll();

        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
            // TODO return http code!!
            return new ArrayList<>(0);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response export() {
        /* Find organizations and Build File */
        String content = OrganizationsDTS.SINGLETON.transform(orgFacade.findAll());
        byte[] b = content.getBytes(Charset.forName("UTF-8"));

        return Response.ok(b).header("Content-Disposition", "attachment; filename=Organizations.csv").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Organization retrieve(@PathParam("id") final int id) {
        return orgFacade.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Organization create(Organization org) {
        try {
            org.setCreated(new GregorianCalendar());
            org.setUpdated(new GregorianCalendar());
            orgFacade.create(org);
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
    public void update(@PathParam("id") final int id, Organization org) {
        org.setUpdated(new GregorianCalendar());
        orgFacade.edit(org);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") final int id) {
        orgFacade.remove(orgFacade.find(id));
    }
}
