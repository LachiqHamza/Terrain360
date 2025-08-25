package com.terrain360.terrain360.entities.questionnaire;

import jakarta.persistence.*;

@Entity
public class ReponseQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    private String choixSelectionne;

    @ManyToOne
    @JoinColumn(name = "reponse_questionnaire_id")
    private ReponseQuestionnaire reponseQuestionnaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getChoixSelectionne() {
        return choixSelectionne;
    }

    public void setChoixSelectionne(String choixSelectionne) {
        this.choixSelectionne = choixSelectionne;
    }

    public ReponseQuestionnaire getReponseQuestionnaire() {
        return reponseQuestionnaire;
    }

    public void setReponseQuestionnaire(ReponseQuestionnaire reponseQuestionnaire) {
        this.reponseQuestionnaire = reponseQuestionnaire;
    }
}
