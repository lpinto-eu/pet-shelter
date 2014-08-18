/**
 * UserFacade.java
 * Created on 12-Aug-2014, 22:01:03
 *
 * petshelter-webapp
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.facades;

import eu.lpinto.petshelter.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * EJB facade for users.
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "eu.lpinto.petshelter_petshelter-webapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UserFacade() {
        super(User.class);
    }

    public User findByName(final String name) {
        try {
        return (User) em.createNamedQuery("User.findByName").setParameter("name", name).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
