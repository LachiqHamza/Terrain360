package com.terrain360.terrain360.controller;

import com.terrain360.terrain360.DTO.CreateEtudeDTO;
import com.terrain360.terrain360.entities.Etude;
import com.terrain360.terrain360.entities.Employe;
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
    public ResponseEntity<Etude> createEtude(@RequestBody CreateEtudeDTO etudeDTO) {
        return ResponseEntity.ok(etudeService.saveEtude(etudeDTO));
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

    @Operation(summary = "Get supervisor by study ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supervisor found",
                    content = @Content(schema = @Schema(implementation = Employe.class))),
            @ApiResponse(responseCode = "404", description = "Study not found")
    })
    @GetMapping("/{id}/superviseur")
    public ResponseEntity<Employe> getSuperviseurByEtude(@PathVariable Long id) {
        return ResponseEntity.ok(etudeService.getSuperviseurByEtude(id));
    }

    @Operation(summary = "Assign investigator to study")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investigator assigned successfully",
                    content = @Content(schema = @Schema(implementation = Etude.class))),
            @ApiResponse(responseCode = "404", description = "Study or employee not found"),
            @ApiResponse(responseCode = "400", description = "Employee is not an investigator")
    })
    @PostMapping("/{etudeId}/enqueteurs/{employeId}")
    public ResponseEntity<Etude> assignEnqueteurToEtude(
            @PathVariable Long etudeId,
            @PathVariable Long employeId
    ) {
        return ResponseEntity.ok(etudeService.assignEnqueteurToEtude(etudeId, employeId));
    }

    @Operation(summary = "Remove investigator from study")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investigator removed successfully",
                    content = @Content(schema = @Schema(implementation = Etude.class))),
            @ApiResponse(responseCode = "404", description = "Study not found")
    })
    @DeleteMapping("/{etudeId}/enqueteurs/{employeId}")
    public ResponseEntity<Etude> removeEnqueteurFromEtude(
            @PathVariable Long etudeId,
            @PathVariable Long employeId
    ) {
        return ResponseEntity.ok(etudeService.removeEnqueteurFromEtude(etudeId, employeId));
    }

    @Operation(summary = "Get studies by supervisor ID")
    @ApiResponse(responseCode = "200", description = "List of studies retrieved successfully",
            content = @Content(schema = @Schema(implementation = Etude.class)))
    @GetMapping("/superviseur/{superviseurId}")
    public ResponseEntity<List<Etude>> getEtudesBySuperviseur(@PathVariable Long superviseurId) {
        return ResponseEntity.ok(etudeService.getEtudesBySuperviseur(superviseurId));
    }

    @Operation(summary = "Get studies by investigator ID")
    @ApiResponse(responseCode = "200", description = "List of studies retrieved successfully",
            content = @Content(schema = @Schema(implementation = Etude.class)))
    @GetMapping("/enqueteur/{employeId}")
    public ResponseEntity<List<Etude>> getEtudesByEnqueteur(@PathVariable Long employeId) {
        return ResponseEntity.ok(etudeService.getEtudesByEnqueteur(employeId));
    }

    @Operation(summary = "Get investigators by study ID")
    @ApiResponse(responseCode = "200", description = "List of investigators retrieved successfully",
            content = @Content(schema = @Schema(implementation = Employe.class)))
    @GetMapping("/{etudeId}/enqueteurs")
    public ResponseEntity<List<Employe>> getEnqueteursByEtude(@PathVariable Long etudeId) {
        return ResponseEntity.ok(etudeService.getEnqueteursByEtude(etudeId));
    }
}