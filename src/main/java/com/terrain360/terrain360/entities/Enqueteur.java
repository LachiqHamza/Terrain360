package com.terrain360.terrain360.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "enqueteur")
@PrimaryKeyJoinColumn(name = "employe_id")
public class Enqueteur extends Employe {
    @OneToMany(mappedBy = "enqueteur", cascade = CascadeType.ALL)
    private List<Tache> taches;

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}