/**
 * Animal.java Created on 12-Aug-2014, 21:15:03
 *
 * petshelter-webapp petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.persistence.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TODO insert a class description
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Entity
@XmlRootElement
@Table(name = "animal")
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a"),
    @NamedQuery(name = "Animal.findById", query = "SELECT a FROM Animal a WHERE a.id = :id"),
    @NamedQuery(name = "Animal.findByCreated", query = "SELECT a FROM Animal a WHERE a.created = :created"),
    @NamedQuery(name = "Animal.findByUpdated", query = "SELECT a FROM Animal a WHERE a.updated = :updated"),
    @NamedQuery(name = "Animal.findByName", query = "SELECT a FROM Animal a WHERE a.name = :name"),
    @NamedQuery(name = "Animal.findByAge", query = "SELECT a FROM Animal a WHERE a.age = :age"),
    @NamedQuery(name = "Animal.findBySpecies", query = "SELECT a FROM Animal a WHERE a.species = :species"),
    @NamedQuery(name = "Animal.findByBreed", query = "SELECT a FROM Animal a WHERE a.breed = :breed"),
    @NamedQuery(name = "Animal.findByDrugs", query = "SELECT a FROM Animal a WHERE a.drugs = :drugs"),
    @NamedQuery(name = "Animal.findBySex", query = "SELECT a FROM Animal a WHERE a.sex = :sex"),
    @NamedQuery(name = "Animal.findByStatus", query = "SELECT a FROM Animal a WHERE a.status = :status"),
    @NamedQuery(name = "Animal.findBySterilized", query = "SELECT a FROM Animal a WHERE a.sterilized = :sterilized"),
    @NamedQuery(name = "Animal.findByObservations", query = "SELECT a FROM Animal a WHERE a.observations = :observations"),
    @NamedQuery(name = "Animal.findByOrganizationId", query = "SELECT a FROM Animal a WHERE a.organization = :organization")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Calendar updated;

    @Size(max = 50)
    @Column(nullable = false)
    private String name;

    private Integer age;

    @Size(max = 30)
    @Column(nullable = false)
    private String species;

    @Size(max = 30)
    private String breed;

    @Size(max = 200)
    private String drugs;

    @Size(max = 1)
    @Column(nullable = false)
    private String sex;

    private Boolean sterilized;

    private Integer status;

    @Lob
    private String picture;

    @Size(max = 250)
    private String observations;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar admission;

    @Size(max = 30)
    private String furPattern;

    private Integer proportion;

    private Float weight;

    @Size(max = 30)
    private String primaryColor;

    @Size(max = 30)
    private String secondaryColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    private Organization organization;

    /*
     * Constructors
     */
    public Animal() {
    }

    public Animal(Integer id) {
        this.id = id;
    }

    public Animal(Integer id, Calendar created, Calendar updated, String name, Integer age, String species, String breed, String drugs, String sex, Boolean sterilized, Integer status, String picture, String observations, Calendar admission, String furPattern, Integer proportion, Float weight, String primaryColor, String secondaryColor, Organization organization) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.name = name;
        this.age = age;
        this.species = species;
        this.breed = breed;
        this.drugs = drugs;
        this.sex = sex;
        this.sterilized = sterilized;
        this.status = status;
        this.picture = picture;
        this.observations = observations;
        this.admission = admission;
        this.furPattern = furPattern;
        this.proportion = proportion;
        this.weight = weight;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.organization = organization;
    }

    /*
     * API
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
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.lpinto.petshelter.entities.Animal[ id=" + id + " ]";
    }

    /*
     * Getters/Listters
     */
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(final Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(final Calendar updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(final String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(final String breed) {
        this.breed = breed;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(final String drugs) {
        this.drugs = drugs;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(final String sex) {
        this.sex = sex;
    }

    public Boolean getSterilized() {
        return sterilized;
    }

    public void setSterilized(final Boolean sterilized) {
        this.sterilized = sterilized;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(final String picture) {
        this.picture = picture;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(final String observations) {
        this.observations = observations;
    }

    public Calendar getAdmission() {
        return admission;
    }

    public void setAdmission(final Calendar admission) {
        this.admission = admission;
    }

    public String getFurPattern() {
        return furPattern;
    }

    public void setFurPattern(final String furPattern) {
        this.furPattern = furPattern;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(final Integer proportion) {
        this.proportion = proportion;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(final Float weight) {
        this.weight = weight;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(final String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(final String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }

}
