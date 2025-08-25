package com.terrain360.terrain360.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "enqueteur")
@DiscriminatorValue("ENQUETEUR")
@PrimaryKeyJoinColumn(name = "employe_id")
public class Enqueteur extends Employe {

    @OneToMany(mappedBy = "enqueteur", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent circular reference
    private List<Tache> taches;

    @ManyToOne
    @JoinColumn(name = "etude_id")
    @JsonBackReference("etude-enqueteurs") // Break circular reference
    private Etude etude;

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public Etude getEtude() {
        return etude;
    }

    public void setEtude(Etude etude) {
        this.etude = etude;
    }
}