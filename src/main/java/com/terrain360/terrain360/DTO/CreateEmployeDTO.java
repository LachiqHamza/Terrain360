package com.terrain360.terrain360.DTO;

import com.terrain360.terrain360.entities.Role;

public class CreateEmployeDTO {
    private String nom;
    private String prenom;
    private String nomDeUtilisateur;
    private String motDePasse;
    private String telephone;
    private String email;
    private Role role;
    private String departement;
    private String poste;

    // Getters and setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getNomDeUtilisateur() { return nomDeUtilisateur; }
    public void setNomDeUtilisateur(String nomDeUtilisateur) { this.nomDeUtilisateur = nomDeUtilisateur; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
}
