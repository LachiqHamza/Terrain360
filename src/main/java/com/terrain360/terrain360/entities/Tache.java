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
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "enqueteur_id")
    private Employe enqueteur;

    @ManyToOne
    @JoinColumn(name = "etude_id")
    private Etude etude;

    // Add getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDateAssignation() { return dateAssignation; }
    public void setDateAssignation(LocalDate dateAssignation) { this.dateAssignation = dateAssignation; }
    public LocalDate getDateEcheance() { return dateEcheance; }
    public void setDateEcheance(LocalDate dateEcheance) { this.dateEcheance = dateEcheance; }
    public int getAppelsPrevus() { return appelsPrevus; }
    public void setAppelsPrevus(int appelsPrevus) { this.appelsPrevus = appelsPrevus; }
    public int getAppelsRealises() { return appelsRealises; }
    public void setAppelsRealises(int appelsRealises) { this.appelsRealises = appelsRealises; }
    public Employe getEnqueteur() { return enqueteur; }
    public void setEnqueteur(Employe enqueteur) { this.enqueteur = enqueteur; }
    public Etude getEtude() { return etude; }
    public void setEtude(Etude etude) { this.etude = etude; }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}