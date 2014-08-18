/**
 * Organization.java
 * Created on 12-Aug-2014, 21:15:03
 *
 * petshelter-webapp
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */

package eu.lpinto.petshelter.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TODO insert a class description
 *
 * @author Luís Pinto -  mail@lpinto.eu
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
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updated;
    @Size(max = 50)
    @Column(name = "name")
    private String name;

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

}
