package com.terrain360.terrain360.controller;

import com.terrain360.terrain360.entities.Superviseur;
import com.terrain360.terrain360.services.implementation.SuperviseurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superviseurs")
@Tag(name = "Supervisor Management", description = "Operations related to supervisors")
public class SuperviseurController {
    private final SuperviseurService superviseurService;

    @Autowired
    public SuperviseurController(SuperviseurService superviseurService) {
        this.superviseurService = superviseurService;
    }

    @Operation(summary = "Get all supervisors")
    @ApiResponse(responseCode = "200", description = "List of supervisors retrieved successfully",
            content = @Content(schema = @Schema(implementation = Superviseur.class)))
    @GetMapping
    public ResponseEntity<List<Superviseur>> getAllSuperviseurs() {
        return ResponseEntity.ok(superviseurService.getAllSuperviseurs());
    }

    @Operation(summary = "Get a supervisor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supervisor found",
                    content = @Content(schema = @Schema(implementation = Superviseur.class))),
            @ApiResponse(responseCode = "404", description = "Supervisor not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Superviseur> getSuperviseurById(@PathVariable Long id) {
        return superviseurService.getSuperviseurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new supervisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Supervisor created successfully",
                    content = @Content(schema = @Schema(implementation = Superviseur.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Superviseur> createSuperviseur(@RequestBody Superviseur superviseur) {
        Superviseur savedSuperviseur = superviseurService.saveSuperviseur(superviseur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSuperviseur);
    }

    @Operation(summary = "Update a supervisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supervisor updated successfully",
                    content = @Content(schema = @Schema(implementation = Superviseur.class))),
            @ApiResponse(responseCode = "404", description = "Supervisor not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Superviseur> updateSuperviseur(@PathVariable Long id, @RequestBody Superviseur updated) {
        return ResponseEntity.ok(superviseurService.updateSuperviseur(id, updated));
    }

    @Operation(summary = "Delete a supervisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supervisor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supervisor not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuperviseur(@PathVariable Long id) {
        superviseurService.deleteSuperviseur(id);
        return ResponseEntity.noContent().build();
    }
}