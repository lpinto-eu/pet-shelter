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
    public List<Integer> users;
    public List<Integer> animals;

    public Organization() {
        super();
    }

    public Organization(final Integer id) {
        super(id);
    }

    public Organization(final String logo, final List<Integer> users, final List<Integer> animals, final Integer id, final String name, final Calendar created, final Calendar updated) {
        super(id, name, created, updated);
        this.logo = logo;
        this.users = users;
        this.animals = animals;
    }
}
