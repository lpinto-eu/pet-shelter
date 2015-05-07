package eu.lpinto.petshelter.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class AnimalDTO extends Animal {

    public static List<Animal> fromList(final List<eu.lpinto.petshelter.persistence.entities.Animal> animals) {
        if (animals == null) {
            return new ArrayList<>(0);
        }

        List<Animal> result = new ArrayList<>(animals.size());

        for (eu.lpinto.petshelter.persistence.entities.Animal animal : animals) {
            result.add(new AnimalDTO(animal));
        };

        return result;
    }
    
    public static List<Animal> fromListIDs(final List<eu.lpinto.petshelter.persistence.entities.Animal> animals) {
        if (animals == null) {
            return new ArrayList<>(0);
        }

        List<Animal> result = new ArrayList<>(animals.size());

        for (eu.lpinto.petshelter.persistence.entities.Animal animal : animals) {
            result.add(new AnimalDTO(animal.getId()));
        };

        return result;
    }

    public AnimalDTO() {
    }

    public AnimalDTO(final Integer id) {
        super(id);
    }

    public AnimalDTO(final eu.lpinto.petshelter.persistence.entities.Animal animal) {
        super(animal.getAge(), animal.getSpecies(), animal.getBreed(), animal.getDrugs(), animal.getSex(), animal.getSterilized(), animal.getStatus(), animal.getPicture(), animal.getObservations(), animal.getAdmission(), animal.getFurPattern(), animal.getProportion(), animal.getWeight(), animal.getPrimaryColor(), animal.getSecondaryColor(),
              animal.getOrganization() == null ? null : new OrganizationDTO(animal.getOrganization().getId()),
              animal.getId(), animal.getName(), animal.getCreated(), animal.getUpdated());
    }
}
