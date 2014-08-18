/**
 * AnimalFacade.java
 * Created on 02-Aug-2014, 21:43:14
 *
 * petshelter-webapp
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.facades;

import eu.lpinto.petshelter.entities.Animal;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB facade for animals.
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Stateless
public class AnimalFacade extends AbstractFacade<Animal> {

    @PersistenceContext(unitName = "eu.lpinto.petshelter_petshelter-webapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AnimalFacade() {
        super(Animal.class);
    }

    public List<Animal> findAllByOrg(final int orgID) {
        List<Animal> result;

        List<Animal> animals = super.findAll();
        result = new ArrayList<>(animals.size());

        for (Animal animal : animals) {
            if (animal.getOrganizationId() == orgID) {
                result.add(animal);
            }
        }

        return result;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
