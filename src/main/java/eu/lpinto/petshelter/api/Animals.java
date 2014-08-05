/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.entities.Animal;
import eu.lpinto.petshelter.facades.AnimalFacade;
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
@Path("animals")
public class Animals {

    @EJB
    private AnimalFacade animalsFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> findAll() {
        try{
            return animalsFacade.findAll();
        } catch(RuntimeException ex) {
            throw ex;
        }
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
    public Animal create(Animal animal) {
        try{
            animal.setCreated(new GregorianCalendar());
            animal.setUpdated(new GregorianCalendar());
            animalsFacade.create(animal);
            return animal;
        } catch(RuntimeException ex) {
            throw ex;
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
