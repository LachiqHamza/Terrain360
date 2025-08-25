package com.terrain360.terrain360.controller.questionnaire;

import com.terrain360.terrain360.entities.questionnaire.ReponseQuestionnaire;
import com.terrain360.terrain360.services.implementation.questionnaire.ReponseQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reponses")
public class ReponseQuestionnaireController {
    private final ReponseQuestionnaireService service;

    @Autowired
    public ReponseQuestionnaireController(ReponseQuestionnaireService service) {
        this.service = service;
    }

    // Save a single response
    @PostMapping("/single")
    public ReponseQuestionnaire saveReponse(@RequestBody ReponseQuestionnaire r) {
        return service.saveReponse(r);
    }

    // Save multiple responses at once
    @PostMapping("/batch")
    public List<ReponseQuestionnaire> submit(@RequestBody List<ReponseQuestionnaire> reponses) {
        return service.saveAll(reponses);
    }
}




