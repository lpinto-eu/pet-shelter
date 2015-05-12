package eu.lpinto.petshelter.api.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class Animal extends Entity implements Serializable {

    public static final long serialVersionUID = 1L;
    public Integer age;
    public String species;
    public String breed;
    public String drugs;
    public String sex;
    public Boolean sterilized;
    public Integer status;
    public String picture;
    public String observations;
    public Calendar admission;
    public String furPattern;
    public Integer proportion;
    public Float weight;
    public String primaryColor;
    public String secondaryColor;
    public Integer organization;

    public Animal() {
        super();
    }

    public Animal(final Integer id) {
        super(id);
    }

    public Animal(Integer age, final String species, final String breed, final String drugs, final String sex, final Boolean sterilized, final Integer status, final String picture, final String observations, final Calendar admission, final String furPattern, final Integer proportion, final Float weight, final String primaryColor, final String secondaryColor, final Integer organization, final Integer id, final String name, final Calendar created, final Calendar updated) {
        super(id, name, created, updated);
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

}
