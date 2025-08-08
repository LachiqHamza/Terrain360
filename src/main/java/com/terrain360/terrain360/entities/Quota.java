package com.terrain360.terrain360.entities;


import jakarta.persistence.*;


@Entity
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private int objectif;
    private int realise;

    @ManyToOne
    @JoinColumn(name = "etude_id")
    private Etude etude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getObjectif() {
        return objectif;
    }

    public void setObjectif(int objectif) {
        this.objectif = objectif;
    }

    public int getRealise() {
        return realise;
    }

    public void setRealise(int realise) {
        this.realise = realise;
    }

    public Etude getEtude() {
        return etude;
    }

    public void setEtude(Etude etude) {
        this.etude = etude;
    }
}
