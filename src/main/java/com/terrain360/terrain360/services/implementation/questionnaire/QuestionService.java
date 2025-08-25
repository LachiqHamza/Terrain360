package com.terrain360.terrain360.services.implementation.questionnaire;

import com.terrain360.terrain360.entities.questionnaire.Question;
import com.terrain360.terrain360.repositories.questionnaire.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repo;

    public Question createQuestion(Question q) {
        return repo.save(q);
    }

    public List<Question> getByQuestionnaire(Long questionnaireId) {
        return repo.findByQuestionnaireId(questionnaireId);
    }
}
