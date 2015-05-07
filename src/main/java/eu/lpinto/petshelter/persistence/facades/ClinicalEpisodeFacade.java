/**
 * ClinicalEpisodeFacade.java
 * Created on Mar 23, 2015, 8:24:35 PM
 *
 * Pet Shelter
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - petshelter.info
 */

package eu.lpinto.petshelter.persistence.facades;

import eu.lpinto.petshelter.persistence.entities.ClinicalEpisode;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * TODO insert a class description
 *
 * @author Vitor Martins
 */
@Stateless
public class ClinicalEpisodeFacade extends AbstractFacade<ClinicalEpisode> {
    @PersistenceContext(unitName = "eu.lpinto.petshelter_petshelter-webapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClinicalEpisodeFacade() {
        super(ClinicalEpisode.class);
    }

}
