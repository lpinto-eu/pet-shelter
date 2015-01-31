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
import java.text.SimpleDateFormat;
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
            if (animal.getOrganization() == orgID) {
                result.add(animal);
            }
        }

        return result;
    }

     public String export() {
        List<Animal> animals = super.findAll();

        if (animals == null) {
            return "";
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Animal animal : animals) {
            sb.append(animal.getId()).append(";");
            sb.append(animal.getName()).append(";");
            sb.append(animal.getOrganization()).append(";");
            sb.append(animal.getAge()).append(";");
            sb.append(animal.getBreed() == null ? "" : animal.getBreed()).append(";");
            sb.append(animal.getSex()== null ? "" : animal.getSex()).append(";");
            sb.append(animal.getSpecies() == null ? "" : animal.getSpecies()).append(";");
            sb.append(animal.getDrugs()== null ? "" : animal.getDrugs()).append(";");
            //sb.append(animal.getPicture()).append(";");
            sb.append(sdf.format(animal.getCreated().getTime())).append(";");
            sb.append(sdf.format(animal.getUpdated().getTime()));
            counter++;

            if (counter != animals.size()) {
                sb.append("\n");
            }

        }

        return sb.toString();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
