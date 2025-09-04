package com.terrain360.terrain360.DTO;

import java.time.LocalDate;

public class CreateEtudeDTO {

    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int objectifQuotas;
    private Long superviseurId;

    // Getters and setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public int getObjectifQuotas() { return objectifQuotas; }
    public void setObjectifQuotas(int objectifQuotas) { this.objectifQuotas = objectifQuotas; }
    public Long getSuperviseurId() { return superviseurId; }
    public void setSuperviseurId(Long superviseurId) { this.superviseurId = superviseurId; }
}
