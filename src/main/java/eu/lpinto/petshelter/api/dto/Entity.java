package eu.lpinto.petshelter.api.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class Entity implements Serializable {

    public Integer id;
    public String name;
    public Calendar created;
    public Calendar updated;

    public Entity() {
    }

    public Entity(final Integer id) {
        this.id = id;
    }

    public Entity(final Integer id, final String name, final Calendar created, final Calendar updated) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }
}
