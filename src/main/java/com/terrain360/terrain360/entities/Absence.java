package com.terrain360.terrain360.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String motif;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
}

