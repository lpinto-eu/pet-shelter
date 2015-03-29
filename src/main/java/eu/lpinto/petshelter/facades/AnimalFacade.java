/**
 * AnimalFacade.java Created on 02-Aug-2014, 21:43:14
 *
 * petshelter-webapp petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.facades;

import eu.lpinto.petshelter.entities.Animal;
import eu.lpinto.petshelter.entities.Organization;
import eu.lpinto.petshelter.entities.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    public List<Animal> findAll(int actionUserID, Integer orgID) {

        User user = em.find(User.class, actionUserID);
        em.refresh(user);
        if (user == null) {
            return null;
        }

        Organization organization = getUserOrganization(user, orgID);
        if (organization == null) {
            return new ArrayList<>(0);
        }

        /* Find All */
        em.refresh(organization);
        return organization.getAnimals();
    }

    public Animal retrieve(int actionUserID, Integer orgID, int animalID) {
        User user = em.find(User.class, actionUserID);
        em.refresh(user);
        if (user == null) {
            return null;
        }

        Organization organization = getUserOrganization(user, orgID);
        if (organization == null) {
            return null;
        }

        /* Find All */
        em.refresh(organization);

        return organization.getAnimal(animalID);
    }

    public Animal create(int actionUserID, Integer orgID, Animal animal) {
        /* Check if User can do action */
        User user = em.find(User.class, actionUserID);
        if (user == null) {
            return null;
        }

        Organization organization = getUserOrganization(user, orgID);
        if (organization == null) {
            return null;
        }

        animal.setCreated(Calendar.getInstance());
        animal.setOrganization(organization);

        super.create(animal);

        return animal;
    }

    protected Organization getUserOrganization(User user, Integer orgID) {
        Organization organization;
        if (orgID == null) {
            if (user.getOrganizations() != null && user.getOrganizations().size() == 1) {
                /* User has only one Org */
                organization = user.getOrganizations().get(0);

            } else {
                return null;
            }

        } else {
            if (!user.hasOrganization(orgID)) {
                return null;
            }

            organization = user.getOrganization(orgID);
        }

        return organization;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
