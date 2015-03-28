/**
 * ClinicalEpisodeTypeFacade.java
 * Created on Mar 23, 2015, 8:24:36 PM
 *
 * Pet Shelter
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - petshelter.info
 */

package eu.lpinto.petshelter.facades;

import eu.lpinto.petshelter.entities.ClinicalEpisodeType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * TODO insert a class description
 *
 * @author Vitor Martins
 */
@Stateless
public class ClinicalEpisodeTypeFacade extends AbstractFacade<ClinicalEpisodeType> {
    @PersistenceContext(unitName = "eu.lpinto.petshelter_petshelter-webapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClinicalEpisodeTypeFacade() {
        super(ClinicalEpisodeType.class);
    }

}
