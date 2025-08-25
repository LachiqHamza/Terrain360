package com.terrain360.terrain360.services.implementation.questionnaire;


import com.terrain360.terrain360.entities.questionnaire.Questionnaire;
import com.terrain360.terrain360.repositories.questionnaire.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireService {
    @Autowired
    private QuestionnaireRepository repo;

    public QuestionnaireService(QuestionnaireRepository repo) {
        this.repo = repo;
    }

    public Questionnaire createQuestionnaire(Questionnaire q) {
        return repo.save(q);
    }

    public List<Questionnaire> getAll() {
        return repo.findAll();
    }
}
