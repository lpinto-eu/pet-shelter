package eu.lpinto.petshelter.api.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class User extends Entity implements Serializable {

    public static final long serialVersionUID = 1L;
    public String email;
//    public String password;
    public List<Integer> organizations;

    public User() {
        super();
    }

    public User(final Integer id) {
        super(id);
    }

    public User(final User user) {
        super(user.id, user.name, user.created, user.updated);
        this.email = user.email;
//        this.password = user.password;
        this.organizations = user.organizations;
    }

    public User(final String email, final String password, final List<Integer> organizations, final Integer id, final String name, final Calendar created, final Calendar updated) {
        super(id, name, created, updated);
        this.email = email;
//        this.password = password;
        this.organizations = organizations;
    }
}
