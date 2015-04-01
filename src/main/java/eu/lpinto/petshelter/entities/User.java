/**
 * User.java Created on 12-Aug-2014, 21:15:03
 *
 * petshelter-webapp petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * TODO insert a class description
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Entity
@Table(name = "ps_user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updated;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")
    @Size(max = 50)
    private String email;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(min = 2, max = 50, message = "Invalid name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Organization",
               joinColumns = {
                   @JoinColumn(name = "ref_user_id", referencedColumnName = "id")},
               inverseJoinColumns = {
                   @JoinColumn(name = "ref_org_id", referencedColumnName = "id")})
    private List<Organization> organizations;

    /*
     * Constructors
     */
    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, Calendar created, Calendar updated, String password, int organizationId) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.password = password;
        this.organizations = Arrays.asList(new Organization(organizationId));
    }

    /*
     * Overide
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.lpinto.petshelter.entities.User[ id=" + id + " ]";
    }

    /*
     * Getters/Setters
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public boolean hasOrganization(int orgID) {
        List<Organization> orgs = getOrganizations();
        if (orgs == null || orgs.isEmpty()) {
            return false;
        }

        for (Organization org : orgs) {
            if (org.getId().equals(orgID)) {
                return true;
            }
        }

        return false;
    }

    public Organization getOrganization(int orgID) {
        List<Organization> orgs = getOrganizations();
        if (orgs == null || orgs.isEmpty()) {
            return null;
        }

        for (Organization org : orgs) {
            if (org.getId().equals(orgID)) {
                return org;
            }
        }

        return null;
    }

    public void addOrg(final Organization org) {
        if (org != null) {
            if (organizations == null) {
                organizations = new ArrayList<>(1);
            }

            organizations.add(org);
        }
    }

    public void rmOrg(final int orgID) {
        if (organizations == null) {
            return;
        }

        Iterator<Organization> iterator = organizations.iterator();
        while (iterator.hasNext()) {
            Organization org = iterator.next();
            if (org.getId().equals(orgID)) {
                iterator.remove();
                break;
            }
        }
    }
}
