package com.terrain360.terrain360.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate dateAssignation;
    private LocalDate dateEcheance;
    private int appelsPrevus;
    private int appelsRealises;

    @ManyToOne
    @JoinColumn(name = "enqueteur_id")
    private Enqueteur enqueteur;

    @ManyToOne
    @JoinColumn(name = "etude_id")
    private Etude etude;
}
