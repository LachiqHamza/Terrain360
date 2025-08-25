package com.terrain360.terrain360.controller.questionnaire;


import com.terrain360.terrain360.entities.questionnaire.Question;
import com.terrain360.terrain360.services.implementation.questionnaire.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@Tag(name = "Questions", description = "Endpoints for managing questions")
public class QuestionController {
    @Autowired
    private QuestionService service;
    @Operation(summary = "Create a question")
    @PostMapping
    public Question create(@RequestBody Question q) {
        return service.createQuestion(q);
    }
    @Operation(summary = "Get questions by questionnaire id")
    @GetMapping("/by-questionnaire/{id}")
    public List<Question> getByQuestionnaire(@PathVariable Long id) {
        return service.getByQuestionnaire(id);
    }
}
