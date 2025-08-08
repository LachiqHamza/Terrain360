package com.terrain360.terrain360.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Salaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;
    private LocalDate datePaiement;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
}
