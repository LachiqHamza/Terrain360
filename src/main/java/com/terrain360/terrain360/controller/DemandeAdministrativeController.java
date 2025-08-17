package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.Contrat;
import com.terrain360.terrain360.entities.DemandeAdministrative;
import com.terrain360.terrain360.services.implementation.DemandeAdministrativeServiceImpl;
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
@RequestMapping("/demandes")
@Tag(name = "Administrative Request Management", description = "Operations related to administrative requests")

public class DemandeAdministrativeController {

    @Autowired
    private  DemandeAdministrativeServiceImpl demandeService;



    public DemandeAdministrativeController(DemandeAdministrativeServiceImpl demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping("/employe/{employeId}")
    @Operation(summary = "Add a contract for an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract added successfully",
                    content = @Content(schema = @Schema(implementation = Contrat.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<DemandeAdministrative> addDemande(@PathVariable Long employeId, @RequestBody DemandeAdministrative demande) {
        return ResponseEntity.ok(demandeService.addDemande(employeId, demande));
    }

    @Operation(summary = "Get all administrative requests for an employee")
    @ApiResponse(responseCode = "200", description = "List of requests retrieved successfully",
            content = @Content(schema = @Schema(implementation = DemandeAdministrative.class)))
    @GetMapping("/employe/{employeId}")

    public ResponseEntity<List<DemandeAdministrative>> getDemandes(@PathVariable Long employeId) {
        return ResponseEntity.ok(demandeService.getDemandesByEmploye(employeId));
    }

    @Operation(summary = "Validate an administrative request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request validated successfully",
                    content = @Content(schema = @Schema(implementation = DemandeAdministrative.class))),
            @ApiResponse(responseCode = "404", description = "Request not found")
    })
    @PutMapping("/{id}/valider")
    public ResponseEntity<DemandeAdministrative> valider(@PathVariable Long id) {
        return ResponseEntity.ok(demandeService.validerDemande(id));
    }

    @Operation(summary = "Reject an administrative request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request rejected successfully",
                    content = @Content(schema = @Schema(implementation = DemandeAdministrative.class))),
            @ApiResponse(responseCode = "404", description = "Request not found")
    })
    @PutMapping("/{id}/refuser")
    public ResponseEntity<DemandeAdministrative> refuser(@PathVariable Long id) {
        return ResponseEntity.ok(demandeService.refuserDemande(id));
    }
}
