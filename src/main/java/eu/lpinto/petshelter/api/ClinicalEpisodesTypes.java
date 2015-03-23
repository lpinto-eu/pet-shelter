package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.api.util.Digest;
import eu.lpinto.petshelter.entities.ClinicalEpisodeType;
import eu.lpinto.petshelter.facades.ClinicalEpisodeTypeFacade;
import eu.lpinto.petshelter.facades.ClinicalEpisodeTypeFacade;
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
 * @author Vítor Martins - varmartins@varmartins.com
 */
@Singleton
@Path("clinical-episode-types")
public class ClinicalEpisodesTypes {

    @EJB
    private ClinicalEpisodeTypeFacade cinicalEpisodeTypes;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClinicalEpisodeType> findAll() {
        return cinicalEpisodeTypes.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ClinicalEpisodeType retrieve(@PathParam("id") final int id) {
        return cinicalEpisodeTypes.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ClinicalEpisodeType create(final ClinicalEpisodeType clinicalEpisodeType) {
        cinicalEpisodeTypes.create(clinicalEpisodeType);
        return clinicalEpisodeType;
    }
    
    @POST
    @Path("load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create() { 
        ClinicalEpisodeType tmp = new ClinicalEpisodeType();
        tmp.setCreated(new GregorianCalendar());
        tmp.setUpdated(new GregorianCalendar());
        tmp.setEnable(true);
        tmp.setName("Vaccine");
        cinicalEpisodeTypes.create(tmp);
        
        tmp = new ClinicalEpisodeType();
        tmp.setCreated(new GregorianCalendar());
        tmp.setUpdated(new GregorianCalendar());
        tmp.setEnable(true);
        tmp.setName("Deworm");
        cinicalEpisodeTypes.create(tmp);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") final int id, final ClinicalEpisodeType clinicalEpisodeType) {
        clinicalEpisodeType.setUpdated(new GregorianCalendar());
        cinicalEpisodeTypes.edit(clinicalEpisodeType);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") final int id) {
        cinicalEpisodeTypes.remove(cinicalEpisodeTypes.find(id));
    }
}
