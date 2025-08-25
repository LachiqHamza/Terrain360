package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.Enqueteur;
import com.terrain360.terrain360.entities.Etude;
import com.terrain360.terrain360.entities.Superviseur;
import com.terrain360.terrain360.services.implementation.EtudeService;
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
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/etudes")
@Tag(name = "Study Management", description = "Operations related to studies")

public class EtudeController {
    @Autowired
    private EtudeService etudeService;

    @Operation(summary = "Get all studies")
    @ApiResponse(responseCode = "200", description = "List of studies retrieved successfully",
            content = @Content(schema = @Schema(implementation = Etude.class)))
    @GetMapping
    public ResponseEntity<List<Etude>> getAllEtudes() {
        return ResponseEntity.ok(etudeService.getAllEtudes());
    }

    @Operation(summary = "Get a study by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Study found",
                    content = @Content(schema = @Schema(implementation = Etude.class))),
            @ApiResponse(responseCode = "404", description = "Study not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Etude> getEtudeById(@PathVariable Long id) {
        Optional<Etude> etude = etudeService.getEtudeById(id);
        return etude.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new study")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Study created successfully",
                    content = @Content(schema = @Schema(implementation = Etude.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Etude> createEtude(@RequestBody Etude etude) {
        return ResponseEntity.ok(etudeService.saveEtude(etude));
    }

    @Operation(summary = "Update a study")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Study updated successfully",
                    content = @Content(schema = @Schema(implementation = Etude.class))),
            @ApiResponse(responseCode = "404", description = "Study not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Etude> updateEtude(@PathVariable Long id, @RequestBody Etude updated) {
        return ResponseEntity.ok(etudeService.updateEtude(id, updated));
    }

    @Operation(summary = "Delete a study")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Study deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Study not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtude(@PathVariable Long id) {
        etudeService.deleteEtude(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/superviseur")
    public ResponseEntity<Superviseur> getSuperviseurByEtude(@PathVariable Long id) {
        return ResponseEntity.ok(etudeService.getSuperviseurByEtude(id));
    }

    @PostMapping("/{id}/enqueteurs")
    public ResponseEntity<Etude> assignEnqueteurToEtude(
            @PathVariable Long id,
            @RequestBody Enqueteur enqueteur
    ) {
        return ResponseEntity.ok(etudeService.assignEnqueteurToEtude(id, enqueteur));
    }
}
