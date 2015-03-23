/**
 * ClinicalEpisode.java Created on Mar 23, 2015, 6:49:03 PM
 *
 * Pet Shelter petshelter-webapp
 *
 * Copyright (c) Pet Shelter - petshelter.info
 */
package eu.lpinto.petshelter.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TODO insert a class description
 *
 * @author Vitor Martins
 */
@Entity
public class ClinicalEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Animal_Id")
    private Animal animal;
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Type_Id")
    private ClinicalEpisodeType type;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;
    @Size(max = 250)
    private String observations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public ClinicalEpisodeType getType() {
        return type;
    }

    public void setType(ClinicalEpisodeType type) {
        this.type = type;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
