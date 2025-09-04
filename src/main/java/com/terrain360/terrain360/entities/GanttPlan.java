package com.terrain360.terrain360.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class GanttPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nombreEnqueteursParJour;
    private String planificationAuto;

    // New fields for enhanced functionality
    private Integer quotas;
    private Integer quotasCompletes;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer progres;
    private String statut; // "on_track", "at_risk", "delayed"
    private String client;
    private String superviseur;
    private Integer enqueteursDisponibles;

    @OneToOne
    @JoinColumn(name = "etude_id")
    private Etude etude;

    // Getters and setters for new fields
    public Integer getQuotas() { return quotas; }
    public void setQuotas(Integer quotas) { this.quotas = quotas; }

    public Integer getQuotasCompletes() { return quotasCompletes; }
    public void setQuotasCompletes(Integer quotasCompletes) { this.quotasCompletes = quotasCompletes; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Integer getProgres() { return progres; }
    public void setProgres(Integer progres) { this.progres = progres; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getSuperviseur() { return superviseur; }
    public void setSuperviseur(String superviseur) { this.superviseur = superviseur; }

    public Integer getEnqueteursDisponibles() { return enqueteursDisponibles; }
    public void setEnqueteursDisponibles(Integer enqueteursDisponibles) { this.enqueteursDisponibles = enqueteursDisponibles; }

    // Existing getters/setters remain...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNombreEnqueteursParJour() { return nombreEnqueteursParJour; }
    public void setNombreEnqueteursParJour(int nombreEnqueteursParJour) { this.nombreEnqueteursParJour = nombreEnqueteursParJour; }
    public String getPlanificationAuto() { return planificationAuto; }
    public void setPlanificationAuto(String planificationAuto) { this.planificationAuto = planificationAuto; }
    public Etude getEtude() { return etude; }
    public void setEtude(Etude etude) { this.etude = etude; }
}