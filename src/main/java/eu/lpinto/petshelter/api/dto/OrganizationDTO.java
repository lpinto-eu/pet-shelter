package eu.lpinto.petshelter.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class OrganizationDTO extends Organization {

    public static List<Organization> fromList(final List<eu.lpinto.petshelter.persistence.entities.Organization> organizations) {
        if (organizations == null) {
            return new ArrayList<>(0);
        }

        List<Organization> result = new ArrayList<>(organizations.size());

        for (eu.lpinto.petshelter.persistence.entities.Organization organization : organizations) {
            result.add(new OrganizationDTO(organization));
        }

        return result;
    }

    public static List<Organization> fromListIDs(final List<eu.lpinto.petshelter.persistence.entities.Organization> organizations) {
        if (organizations == null) {
            return new ArrayList<>(0);
        }

        List<Organization> result = new ArrayList<>(organizations.size());

        for (eu.lpinto.petshelter.persistence.entities.Organization organization : organizations) {
            result.add(new OrganizationDTO(organization.getId()));
        }

        return result;
    }

    public OrganizationDTO() {
        super();
    }

    public OrganizationDTO(final Integer id) {
        super(id);
    }

    public OrganizationDTO(final eu.lpinto.petshelter.persistence.entities.Organization organization) {
        super(organization.getLogo(),
              UserDTO.fromListIDs(organization.getUsers()),
              AnimalDTO.fromListIDs(organization.getAnimals()),
              organization.getId(), organization.getName(), organization.getCreated(), organization.getUpdated(), organization.getCapacity());
    }
}
