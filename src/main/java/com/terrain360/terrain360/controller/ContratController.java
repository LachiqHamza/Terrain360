package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.Contrat;
import com.terrain360.terrain360.services.implementation.ContratServiceImpl;
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
@RequestMapping("/contrats")
@Tag(name = "Contract Management", description = "Operations related to employee contracts")

public class ContratController {

    @Autowired
    private  ContratServiceImpl contratService;



    public ContratController(ContratServiceImpl contratService) {
        this.contratService = contratService;
    }


    @Operation(summary = "Add a contract for an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract added successfully",
                    content = @Content(schema = @Schema(implementation = Contrat.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PostMapping("/employe/{employeId}")
    public ResponseEntity<Contrat> addContrat(@PathVariable Long employeId, @RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.addContrat(employeId, contrat));
    }

    @Operation(summary = "Get all contracts for an employee")
    @ApiResponse(responseCode = "200", description = "List of contracts retrieved successfully",
            content = @Content(schema = @Schema(implementation = Contrat.class)))
    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<Contrat>> getContrats(@PathVariable Long employeId) {
        return ResponseEntity.ok(contratService.getContratsByEmploye(employeId));
    }
}
