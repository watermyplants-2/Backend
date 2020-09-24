package com.buildweekjava.watermyplant2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "plants")
public class Plant{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long plantid;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "roles", allowSetters = true)

    private User user;

    private String nickname;
    private String species;
    private String h2oFrequency;

    public Plant() {
    }

    public Plant(User user, String nickname, String species, String h2oFrequency) {
        this.user = user;
        this.nickname = nickname;
        this.species = species;
        this.h2oFrequency = h2oFrequency;
    }

    public long getPlantid() {
        return plantid;
    }

    public void setPlantid(long plantid) {
        this.plantid = plantid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getH2oFrequency() {
        return h2oFrequency;
    }

    public void setH2oFrequency(String h2oFrequency) {
        this.h2oFrequency = h2oFrequency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


