package com.terrain360.terrain360.entities;

import jakarta.persistence.*;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ville;
    private String quartier;
    private String codePostal;

    @ManyToOne
    @JoinColumn(name = "etude_id")
    private Etude etude;
}
