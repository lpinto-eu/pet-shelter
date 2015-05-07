package eu.lpinto.petshelter.api.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class Organization extends Entity implements Serializable {

    public static final long serialVersionUID = 1L;
    public String logo;
    public List<User> users;
    public List<Animal> animals;
    public Integer capacity;

    public Organization() {
        super();
    }

    public Organization(final Integer id) {
        super(id);
    }

    public Organization(final String logo, final List<User> users, final List<Animal> animals, final Integer id, final String name, final Calendar created, final Calendar updated, final Integer capacity) {
        super(id, name, created, updated);
        this.logo = logo;
        this.users = users;
        this.animals = animals;
        this.capacity = capacity;
    }

}
