package com.terrain360.terrain360.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String tele;
    private String adresse;
    private String poste;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Contrat> contrats;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Absence> absences;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Salaire> salaires;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<DemandeAdministrative> demandes;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public List<Salaire> getSalaires() {
        return salaires;
    }

    public void setSalaires(List<Salaire> salaires) {
        this.salaires = salaires;
    }

    public List<DemandeAdministrative> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<DemandeAdministrative> demandes) {
        this.demandes = demandes;
    }
}
