package eu.lpinto.petshelter.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class UserDTO extends User {

    /*
     * Domain to DTO
     */
    public static List<User> valueOf(final List<eu.lpinto.petshelter.persistence.entities.User> users) {
        if (users == null) {
            return new ArrayList<>(0);
        }

        List<User> result = new ArrayList<>(users.size());

        for (eu.lpinto.petshelter.persistence.entities.User user : users) {
            result.add(new UserDTO(user));
        }

        return result;
    }

    public static List<Integer> valueOfIDs(final List<eu.lpinto.petshelter.persistence.entities.User> users) {
        if (users == null) {
            return new ArrayList<>(0);
        }

        List<Integer> result = new ArrayList<>(users.size());

        for (eu.lpinto.petshelter.persistence.entities.User user : users) {
            result.add(user.getId());
        }

        return result;
    }

    /*
     * DTO to Domain
     */
    public static List<eu.lpinto.petshelter.persistence.entities.User> entities(final List<Integer> users) {
        if (users == null) {
            return null;
        }

        List<eu.lpinto.petshelter.persistence.entities.User> result = new ArrayList<>(users.size());

        for (Integer userID : users) {
            result.add(new eu.lpinto.petshelter.persistence.entities.User(userID));
        }

        return result;
    }

    /*
     * Constructors
     */
    public UserDTO() {
        super();
    }

    public UserDTO(final Integer id) {
        super(id);
    }

    public UserDTO(final User user) {
        super(user.email, user.name, user.organizations, user.id, user.name, user.created, user.updated);
    }

    public UserDTO(final eu.lpinto.petshelter.persistence.entities.User user) {
        super(user.getEmail(), user.getPassword(),
              OrganizationDTO.valueOfIDs(user.getOrganizations()),
              user.getId(), user.getName(), user.getCreated(), user.getUpdated());
    }

    /*
     * DTO to Domain
     */
    public eu.lpinto.petshelter.persistence.entities.User entity() {
        return new eu.lpinto.petshelter.persistence.entities.User(id, created, updated, email, name, name, null);
    }
}
