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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/superviseurs")
@Tag(name = "Supervisor Management", description = "Operations related to supervisors")

public class SuperviseurController {

    @Autowired
    private  SuperviseurService superviseurService;

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
        Optional<Superviseur> superviseur = superviseurService.getSuperviseurById(id);
        return superviseur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new supervisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supervisor created successfully",
                    content = @Content(schema = @Schema(implementation = Superviseur.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Superviseur> createSuperviseur(@RequestBody Superviseur superviseur) {
        return ResponseEntity.ok(superviseurService.saveSuperviseur(superviseur));
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a supervisor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supervisor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supervisor not found")
    })
    public ResponseEntity<Void> deleteSuperviseur(@PathVariable Long id) {
        superviseurService.deleteSuperviseur(id);
        return ResponseEntity.noContent().build();
    }
}