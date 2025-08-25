package com.terrain360.terrain360.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "etude")
public class Etude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int objectifQuotas; // ✅ keep this

    // 🔹 Many Etudes belong to one Superviseur
    @ManyToOne
    @JoinColumn(name = "superviseur_id")
    @JsonBackReference("superviseur-etudes") // Break circular reference
    private Superviseur superviseur;

    // 🔹 One Etude can have many Enqueteurs
    @OneToMany(mappedBy = "etude", cascade = CascadeType.ALL)
    @JsonManagedReference("etude-enqueteurs") // Break circular reference
    private List<Enqueteur> enqueteurs;

    // 🔹 One Etude can have many Quotas
    @OneToMany(mappedBy = "etude", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent circular reference
    private List<Quota> quotas;

    // 🔹 One Etude can have many Adresses
    @OneToMany(mappedBy = "etude", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent circular reference
    private List<Adresse> adresses;

    // 🔹 One Etude can have many Taches
    @OneToMany(mappedBy = "etude", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent circular reference
    private List<Tache> taches;

    // 🔹 One Etude can have one GanttPlan
    @OneToOne(mappedBy = "etude", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent circular reference
    private GanttPlan ganttPlan;

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public int getObjectifQuotas() { return objectifQuotas; }
    public void setObjectifQuotas(int objectifQuotas) { this.objectifQuotas = objectifQuotas; }

    public Superviseur getSuperviseur() { return superviseur; }
    public void setSuperviseur(Superviseur superviseur) { this.superviseur = superviseur; }

    public List<Enqueteur> getEnqueteurs() { return enqueteurs; }
    public void setEnqueteurs(List<Enqueteur> enqueteurs) { this.enqueteurs = enqueteurs; }

    public List<Quota> getQuotas() { return quotas; }
    public void setQuotas(List<Quota> quotas) { this.quotas = quotas; }

    public List<Adresse> getAdresses() { return adresses; }
    public void setAdresses(List<Adresse> adresses) { this.adresses = adresses; }

    public List<Tache> getTaches() { return taches; }
    public void setTaches(List<Tache> taches) { this.taches = taches; }

    public GanttPlan getGanttPlan() { return ganttPlan; }
    public void setGanttPlan(GanttPlan ganttPlan) { this.ganttPlan = ganttPlan; }
}