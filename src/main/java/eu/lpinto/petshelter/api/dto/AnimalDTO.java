package eu.lpinto.petshelter.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class AnimalDTO extends Animal {

    /*
     * Domain to DTO
     */
    public static List<Animal> valueOf(final List<eu.lpinto.petshelter.persistence.entities.Animal> animals) {
        if (animals == null) {
            return new ArrayList<>(0);
        }

        List<Animal> result = new ArrayList<>(animals.size());

        for (eu.lpinto.petshelter.persistence.entities.Animal animal : animals) {
            result.add(new AnimalDTO(animal));
        };

        return result;
    }

    public static List<Integer> valueOfIDs(final List<eu.lpinto.petshelter.persistence.entities.Animal> animals) {
        if (animals == null) {
            return new ArrayList<>(0);
        }

        List<Integer> result = new ArrayList<>(animals.size());

        for (eu.lpinto.petshelter.persistence.entities.Animal animal : animals) {
            result.add(animal.getId());
        };

        return result;
    }

    /*
     * DTO to Domain
     */
    public static List<eu.lpinto.petshelter.persistence.entities.Animal> entities(final List<Integer> users) {
        if (users == null) {
            return null;
        }

        List<eu.lpinto.petshelter.persistence.entities.Animal> result = new ArrayList<>(users.size());

        for (Integer animalID : users) {
            result.add(new eu.lpinto.petshelter.persistence.entities.Animal(animalID));
        }

        return result;
    }

    /*
     * Constructors
     */
    public AnimalDTO() {
    }

    public AnimalDTO(final Integer id) {
        super(id);
    }

    public AnimalDTO(final eu.lpinto.petshelter.persistence.entities.Animal animal) {
        super(animal.getAge(), animal.getSpecies(), animal.getBreed(), animal.getDrugs(), animal.getSex(), animal.getSterilized(), animal.getStatus(), animal.getPicture(), animal.getObservations(), animal.getAdmission(), animal.getFurPattern(), animal.getProportion(), animal.getWeight(), animal.getPrimaryColor(), animal.getSecondaryColor(),
              animal.getOrganization() == null ? null : animal.getOrganization().getId(),
              animal.getId(), animal.getName(), animal.getCreated(), animal.getUpdated());
    }

    /*
     * DTO to Domain
     */
    public eu.lpinto.petshelter.persistence.entities.Animal entity() {
        return new eu.lpinto.petshelter.persistence.entities.Animal(id, created, updated, name, age, species, breed, drugs, sex, sterilized, status, picture, observations, admission, furPattern, proportion, weight, primaryColor, secondaryColor, null, new eu.lpinto.petshelter.persistence.entities.Organization(organization));
    }
}
