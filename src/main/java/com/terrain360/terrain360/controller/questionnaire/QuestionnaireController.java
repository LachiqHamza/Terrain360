package com.terrain360.terrain360.controller.questionnaire;

import com.terrain360.terrain360.entities.questionnaire.Questionnaire;
import com.terrain360.terrain360.services.implementation.questionnaire.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService service;

    public QuestionnaireController(QuestionnaireService service) {
        this.service = service;
    }

    @PostMapping
    public Questionnaire create(@RequestBody Questionnaire q) {
        return service.createQuestionnaire(q);
    }

    @GetMapping
    public List<Questionnaire> getAll() {
        return service.getAll();
    }
}
