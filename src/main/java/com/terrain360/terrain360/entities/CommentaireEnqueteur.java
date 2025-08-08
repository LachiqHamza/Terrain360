package com.terrain360.terrain360.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CommentaireEnqueteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentaire;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "superviseur_id")
    private Superviseur superviseur;

    @ManyToOne
    @JoinColumn(name = "enqueteur_id")
    private Enqueteur enqueteur;
}
