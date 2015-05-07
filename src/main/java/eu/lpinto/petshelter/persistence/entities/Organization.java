/**
 * Organization.java Created on 12-Aug-2014, 21:15:03
 *
 * petshelter-webapp petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * TODO insert a class description
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Entity
@Table(name = "organization")
@NamedQueries({
    @NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o"),
    @NamedQuery(name = "Organization.findById", query = "SELECT o FROM Organization o WHERE o.id = :id"),
    @NamedQuery(name = "Organization.findByCreated", query = "SELECT o FROM Organization o WHERE o.created = :created"),
    @NamedQuery(name = "Organization.findByUpdated", query = "SELECT o FROM Organization o WHERE o.updated = :updated"),
    @NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name")})
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Calendar updated;

    @Size(max = 100)
    @Column(unique = true, nullable = false)
    private String name;

    @Lob
    private String logo;

    @ManyToMany(mappedBy = "organizations", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<Animal> animals;


    /* Constructors */
    public Organization() {
    }

    public Organization(Integer id) {
        this.id = id;
    }

    public Organization(Integer id, Calendar created, Calendar updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    /* Getters/Setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.lpinto.petshelter.entities.Organization[ id=" + id + " ]";
    }

    public void addUser(final User user) {
        if (user != null) {
            if (users == null) {
                users = new ArrayList<>(1);
            }

            users.add(user);
        }
    }

    public void rmUser(final int userID) {
        if (users == null) {
            return;
        }

        List<User> usrs = getUsers();
        User target = null;
        for (User user : usrs) {
            if (user.getId().equals(userID)) {
                target = user;
                break;
            }
        }

        if (target != null) {
            users.remove(target);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean hasUser(int userID) {
        List<User> usrs = getUsers();
        if (usrs == null || usrs.isEmpty()) {
            return false;
        }

        for (User user : usrs) {
            if (user.getId().equals(userID)) {
                return true;
            }
        }

        return false;
    }

    public Animal getAnimal(int animalID) {
        List<Animal> anmls = getAnimals();
        if (anmls == null || anmls.isEmpty()) {
            return null;
        }

        for (Animal animal : anmls) {
            if (animal.getId().equals(animalID)) {
                return animal;
            }
        }

        return null;
    }

}
