package com.terrain360.terrain360.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String tele;
    private String adresse;
    private String poste;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Contrat> contrats;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Absence> absences;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Salaire> salaires;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<DemandeAdministrative> demandes;
}
