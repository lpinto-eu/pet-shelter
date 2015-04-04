/**
 * AnimalFacade.java Created on 02-Aug-2014, 21:43:14
 *
 * petshelter-webapp petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.facades;

import eu.lpinto.petshelter.entities.Animal;
import java.util.Calendar;
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

    /* Constructors */
    public AnimalFacade() {
        super(Animal.class);
    }

    /* CRUD */
    @Override
    public void create(final Animal animal) {
        if (animal.getOrganization() == null) {
            throw new IllegalArgumentException("Cannot create animal '" + animal.getName() + "' with no organization.");
        }

        Calendar now = Calendar.getInstance();
        animal.setCreated(now);
        animal.setUpdated(now);

        super.create(animal);
    }

    @Override
    public void edit(Animal animal) {
        if (animal.getOrganization() == null) {
            throw new IllegalArgumentException("Cannot update animal '" + animal.getName() + "' with no organization.");
        }

        animal.setUpdated(Calendar.getInstance());
        super.edit(animal);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
