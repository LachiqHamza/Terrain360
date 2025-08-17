package com.terrain360.terrain360.controller;

import com.terrain360.terrain360.entities.Salaire;
import com.terrain360.terrain360.services.implementation.SalaireServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salaires")
@Tag(name = "Salary Management", description = "Operations related to employee salaries")

public class SalaireController {

    @Autowired
    private  SalaireServiceImpl salaireService;

    @Operation(summary = "Add a salary for an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salary added successfully",
                    content = @Content(schema = @Schema(implementation = Salaire.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PostMapping("/employe/{employeId}")
    public ResponseEntity<Salaire> addSalaire(@PathVariable Long employeId, @RequestBody Salaire salaire) {
        Salaire savedSalaire = salaireService.addSalaire(employeId, salaire);
        return savedSalaire != null ? ResponseEntity.ok(savedSalaire) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all salaries for an employee")
    @ApiResponse(responseCode = "200", description = "List of salaries retrieved successfully",
            content = @Content(schema = @Schema(implementation = Salaire.class)))
    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<Salaire>> getSalairesByEmployee(@PathVariable Long employeId) {
        List<Salaire> salaires = salaireService.getSalairesByEmploye(employeId);
        return ResponseEntity.ok(salaires);
    }
}