/**
 * ClinicalEpisodeType.java
 * Created on Mar 23, 2015, 6:56:30 PM
 *
 * Pet Shelter
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - petshelter.info
 */

package eu.lpinto.petshelter.entities;

import java.util.Calendar;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class ClinicalEpisodeType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private long id;
    @OneToMany(mappedBy = "type")    
    private Set<ClinicalEpisode> clinicalEpisodes;
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updated;
    @NotNull
    private boolean enable;        
    
    public long getId() {
        return id;
    }

    public Set<ClinicalEpisode> getClinicalEpisodes() {
        return clinicalEpisodes;
    }

    public void setClinicalEpisodes(Set<ClinicalEpisode> clinicalEpisodes) {
        this.clinicalEpisodes = clinicalEpisodes;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Calendar getCreated() {
        return created;
    }
    
    public void setCreated(Calendar created) {
        this.created = created;
    }
    
    public Calendar getUpdated () {
        return updated;
    }
    
    public void setUpdated (Calendar updated) {
        this.updated = updated;
    }
    
    public boolean getEnable () {
        return enable;
    }
    
    public void setEnable(boolean enable) {
        this.enable = enable;
    }        
}
