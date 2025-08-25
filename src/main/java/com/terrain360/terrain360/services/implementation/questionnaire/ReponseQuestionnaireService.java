package com.terrain360.terrain360.services.implementation.questionnaire;


import com.terrain360.terrain360.entities.questionnaire.ReponseQuestionnaire;
import com.terrain360.terrain360.repositories.questionnaire.ReponseQuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseQuestionnaireService {
    @Autowired
    private ReponseQuestionnaireRepository repo;

    public ReponseQuestionnaireService(ReponseQuestionnaireRepository repo) {
        this.repo = repo;
    }

    public ReponseQuestionnaire saveReponse(ReponseQuestionnaire r) {
        return repo.save(r);
    }
    public List<ReponseQuestionnaire> saveAll(List<ReponseQuestionnaire> reponses) {
        return repo.saveAll(reponses);
    }
}
