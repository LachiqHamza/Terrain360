package com.terrain360.terrain360.controller.questionnaire;

import com.terrain360.terrain360.entities.questionnaire.ReponseQuestionnaire;
import com.terrain360.terrain360.services.implementation.questionnaire.ReponseQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ReponseQuestionnaire> saveReponse(@RequestBody ReponseQuestionnaire r) {
        return ResponseEntity.ok(service.saveReponse(r));
    }

    // Save multiple responses at once
    @PostMapping("/batch")
    public ResponseEntity<List<ReponseQuestionnaire>> submit(@RequestBody List<ReponseQuestionnaire> reponses) {
        return ResponseEntity.ok(service.saveAll(reponses));
    }

    // Get responses by investigator
    @GetMapping("/enqueteur/{employeId}")
    public ResponseEntity<List<ReponseQuestionnaire>> getReponsesByEnqueteur(@PathVariable Long employeId) {
        return ResponseEntity.ok(service.getReponsesByEnqueteur(employeId));
    }

    // Get responses by questionnaire
    @GetMapping("/questionnaire/{questionnaireId}")
    public ResponseEntity<List<ReponseQuestionnaire>> getReponsesByQuestionnaire(@PathVariable Long questionnaireId) {
        return ResponseEntity.ok(service.getReponsesByQuestionnaire(questionnaireId));
    }
}