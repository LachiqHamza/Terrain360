package com.terrain360.terrain360.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Enqueteur extends Employe{
    @OneToMany(mappedBy = "enqueteur", cascade = CascadeType.ALL)
    private List<Tache> taches;
}
