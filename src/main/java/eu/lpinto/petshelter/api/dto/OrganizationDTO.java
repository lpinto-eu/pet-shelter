package eu.lpinto.petshelter.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class OrganizationDTO extends Organization {

    /*
     * Domain to DTO
     */
    public static List<OrganizationDTO> valueOf(final List<eu.lpinto.petshelter.persistence.entities.Organization> organizations) {
        if (organizations == null) {
            return new ArrayList<>(0);
        }

        List<OrganizationDTO> result = new ArrayList<>(organizations.size());

        for (eu.lpinto.petshelter.persistence.entities.Organization organization : organizations) {
            result.add(new OrganizationDTO(organization));
        }

        return result;
    }

    public static List<Integer> valueOfIDs(final List<eu.lpinto.petshelter.persistence.entities.Organization> organizations) {
        if (organizations == null) {
            return new ArrayList<>(0);
        }

        List<Integer> result = new ArrayList<>(organizations.size());

        for (eu.lpinto.petshelter.persistence.entities.Organization organization : organizations) {
            result.add(organization.getId());
        }

        return result;
    }

    /*
     * DTO to Domain
     */
    public static List<eu.lpinto.petshelter.persistence.entities.Organization> entities(final List<Integer> users) {
        if (users == null) {
            return null;
        }

        List<eu.lpinto.petshelter.persistence.entities.Organization> result = new ArrayList<>(users.size());

        for (Integer orgID : users) {
            result.add(new eu.lpinto.petshelter.persistence.entities.Organization(orgID));
        }

        return result;
    }

    /*
     * Constructors
     */
    public OrganizationDTO() {
        super();
    }

    public OrganizationDTO(final Integer id) {
        super(id);
    }

    public OrganizationDTO(final eu.lpinto.petshelter.persistence.entities.Organization organization) {
        super(organization.getLogo(),
              UserDTO.valueOfIDs(organization.getUsers()),
              AnimalDTO.valueOfIDs(organization.getAnimals()),
              organization.getId(), organization.getName(), organization.getCreated(), organization.getUpdated());
    }

    /*
     * DTO to Domain
     */
    public eu.lpinto.petshelter.persistence.entities.Organization entity() {
        return new eu.lpinto.petshelter.persistence.entities.Organization(id, created, updated, name, logo, UserDTO.entities(users), AnimalDTO.entities(animals));
    }
}
