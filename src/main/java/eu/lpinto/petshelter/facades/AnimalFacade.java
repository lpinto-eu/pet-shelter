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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * TODO insert a class description
 *
 * @author Luís Pinto -  mail@lpinto.eu
 */
@Stateless
public class AnimalFacade extends AbstractFacade<Animal> {
    @PersistenceContext(unitName = "eu.lpinto.petshelter_petshelter-webapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnimalFacade() {
        super(Animal.class);
    }

}
