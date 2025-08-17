package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.Enqueteur;
import com.terrain360.terrain360.services.implementation.EnqueteurService;
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

@RestController
@RequestMapping("/api/enqueteurs")
@Tag(name = "Investigator Management", description = "Operations related to investigators")

public class EnqueteurController {
    @Autowired
    private EnqueteurService enqueteurService;

    @Operation(summary = "Get all investigators")
    @ApiResponse(responseCode = "200", description = "List of investigators retrieved successfully",
            content = @Content(schema = @Schema(implementation = Enqueteur.class)))
    @GetMapping
    public ResponseEntity<List<Enqueteur>> getAllEnqueteurs() {
        return ResponseEntity.ok(enqueteurService.getAllEnqueteurs());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an investigator by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investigator found",
                    content = @Content(schema = @Schema(implementation = Enqueteur.class))),
            @ApiResponse(responseCode = "404", description = "Investigator not found")
    })
    public ResponseEntity<Enqueteur> getEnqueteurById(@PathVariable Long id) {
        Optional<Enqueteur> enqueteur = enqueteurService.getEnqueteurById(id);
        return enqueteur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new investigator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investigator created successfully",
                    content = @Content(schema = @Schema(implementation = Enqueteur.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Enqueteur> createEnqueteur(@RequestBody Enqueteur enqueteur) {
        return ResponseEntity.ok(enqueteurService.saveEnqueteur(enqueteur));
    }

    @Operation(summary = "Update an investigator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investigator updated successfully",
                    content = @Content(schema = @Schema(implementation = Enqueteur.class))),
            @ApiResponse(responseCode = "404", description = "Investigator not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Enqueteur> updateEnqueteur(@PathVariable Long id, @RequestBody Enqueteur updated) {
        return ResponseEntity.ok(enqueteurService.updateEnqueteur(id, updated));
    }

    @Operation(summary = "Delete an investigator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Investigator deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Investigator not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnqueteur(@PathVariable Long id) {
        enqueteurService.deleteEnqueteur(id);
        return ResponseEntity.noContent().build();
    }
}
