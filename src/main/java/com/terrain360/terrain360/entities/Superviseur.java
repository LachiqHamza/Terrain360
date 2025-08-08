package com.terrain360.terrain360.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Superviseur extends Employe{
    @OneToMany(mappedBy = "superviseur", cascade = CascadeType.ALL)
    private List<Etude> etudes;

    @OneToMany(mappedBy = "superviseur", cascade = CascadeType.ALL)
    private List<CommentaireEnqueteur> commentaires;
}
