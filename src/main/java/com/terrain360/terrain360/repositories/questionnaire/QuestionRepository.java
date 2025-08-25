package com.terrain360.terrain360.repositories.questionnaire;

import com.terrain360.terrain360.entities.questionnaire.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuestionnaireId(Long questionnaireId);
}
