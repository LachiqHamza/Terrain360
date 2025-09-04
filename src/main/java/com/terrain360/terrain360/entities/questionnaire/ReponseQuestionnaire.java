package com.terrain360.terrain360.entities.questionnaire;


import com.terrain360.terrain360.entities.Employe;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ReponseQuestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employe enqueteur; // Changed from Enqueteur to Employe

    @ManyToOne
    private Questionnaire questionnaire;

    @OneToMany(mappedBy = "reponseQuestionnaire", cascade = CascadeType.ALL)
    private List<ReponseQuestion> reponses;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Employe getEnqueteur() { return enqueteur; }
    public void setEnqueteur(Employe enqueteur) { this.enqueteur = enqueteur; }
    public Questionnaire getQuestionnaire() { return questionnaire; }
    public void setQuestionnaire(Questionnaire questionnaire) { this.questionnaire = questionnaire; }
    public List<ReponseQuestion> getReponses() { return reponses; }
    public void setReponses(List<ReponseQuestion> reponses) { this.reponses = reponses; }
}
