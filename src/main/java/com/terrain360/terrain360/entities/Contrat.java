package com.terrain360.terrain360.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String typeContrat;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
}
