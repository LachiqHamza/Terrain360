package com.terrain360.terrain360.entities;


import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class DemandeAdministrative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeDemande; // ex: Attestation, Congé, etc.
    private String statut;      // en attente, validée, refusée
    private LocalDate dateDemande;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
}
