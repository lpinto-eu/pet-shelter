/**
 * OrganizationFacade.java
 * Created on 12-Aug-2014, 22:01:03
 *
 * petshelter-webapp
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.facades;

import eu.lpinto.petshelter.entities.Organization;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB facade for organisations.
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Stateless
public class OrganizationFacade extends AbstractFacade<Organization> {

    @PersistenceContext(unitName = "eu.lpinto.petshelter_petshelter-webapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public OrganizationFacade() {
        super(Organization.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
