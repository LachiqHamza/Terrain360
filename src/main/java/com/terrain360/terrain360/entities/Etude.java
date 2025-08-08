package com.terrain360.terrain360.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Etude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int objectifQuotas;



    @ManyToOne
    @JoinColumn(name = "superviseur_id")
    private Superviseur superviseur;

    @OneToMany(mappedBy = "etude", cascade = CascadeType.ALL)
    private List<Quota> quotas;

    @OneToMany(mappedBy = "etude", cascade = CascadeType.ALL)
    private List<Adresse> adresses;

    @OneToMany(mappedBy = "etude", cascade = CascadeType.ALL)
    private List<Tache> taches;

    @OneToOne(mappedBy = "etude", cascade = CascadeType.ALL)
    private GanttPlan ganttPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getObjectifQuotas() {
        return objectifQuotas;
    }

    public void setObjectifQuotas(int objectifQuotas) {
        this.objectifQuotas = objectifQuotas;
    }

    public Superviseur getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(Superviseur superviseur) {
        this.superviseur = superviseur;
    }

    public List<Quota> getQuotas() {
        return quotas;
    }

    public void setQuotas(List<Quota> quotas) {
        this.quotas = quotas;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public GanttPlan getGanttPlan() {
        return ganttPlan;
    }

    public void setGanttPlan(GanttPlan ganttPlan) {
        this.ganttPlan = ganttPlan;
    }
}
