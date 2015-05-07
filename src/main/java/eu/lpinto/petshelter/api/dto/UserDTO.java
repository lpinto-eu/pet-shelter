package eu.lpinto.petshelter.api.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class UserDTO extends User {

    public static List<User> fromList(final List<eu.lpinto.petshelter.persistence.entities.User> users) {
        if (users == null) {
            return new ArrayList<>(0);
        }

        List<User> result = new ArrayList<>(users.size());

        users.stream().forEach((user) -> {
            result.add(new UserDTO(user));
        });

        return result;
    }
    
    public static List<User> fromListIDs(final List<eu.lpinto.petshelter.persistence.entities.User> users) {
        if (users == null) {
            return new ArrayList<>(0);
        }

        List<User> result = new ArrayList<>(users.size());

        for(eu.lpinto.petshelter.persistence.entities.User user : users) {
            result.add(new UserDTO(user.getId()));
        }

        return result;
    }

    public UserDTO() {
        super();
    }

    public UserDTO(final Integer id) {
        super(id);
    }

    public UserDTO(final eu.lpinto.petshelter.persistence.entities.User user) {
        super(user.getEmail(), user.getPassword(),
              OrganizationDTO.fromListIDs(user.getOrganizations()),
              user.getId(), user.getName(), user.getCreated(), user.getUpdated());
    }
}
