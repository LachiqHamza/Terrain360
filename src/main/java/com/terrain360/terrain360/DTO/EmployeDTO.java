package com.terrain360.terrain360.DTO;

import com.terrain360.terrain360.entities.Role;

import java.time.LocalDate;

public class EmployeDTO {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String adresse;
    private String nomDeUtilisateur;
    private String telephone;
    private String imageProfil;
    private String genre;
    private Role role;
    private String email;
    private LocalDate dateEmbauche;
    private String matricule;
    private String departement;
    private String poste;
    private Double salaire;
    private Boolean actif;

    // Constructors
    public EmployeDTO() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getNomDeUtilisateur() { return nomDeUtilisateur; }
    public void setNomDeUtilisateur(String nomDeUtilisateur) { this.nomDeUtilisateur = nomDeUtilisateur; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getImageProfil() { return imageProfil; }
    public void setImageProfil(String imageProfil) { this.imageProfil = imageProfil; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche; }
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
    public Double getSalaire() { return salaire; }
    public void setSalaire(Double salaire) { this.salaire = salaire; }
    public Boolean getActif() { return actif; }
    public void setActif(Boolean actif) { this.actif = actif; }
}

