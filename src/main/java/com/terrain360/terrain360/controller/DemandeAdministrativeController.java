package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.DemandeAdministrative;
import com.terrain360.terrain360.services.implementation.DemandeAdministrativeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandes")
public class DemandeAdministrativeController {
    private final DemandeAdministrativeServiceImpl demandeService;

    public DemandeAdministrativeController() {
        this(null);
    }

    public DemandeAdministrativeController(DemandeAdministrativeServiceImpl demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping("/employe/{employeId}")
    public ResponseEntity<DemandeAdministrative> addDemande(@PathVariable Long employeId, @RequestBody DemandeAdministrative demande) {
        return ResponseEntity.ok(demandeService.addDemande(employeId, demande));
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<DemandeAdministrative>> getDemandes(@PathVariable Long employeId) {
        return ResponseEntity.ok(demandeService.getDemandesByEmploye(employeId));
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<DemandeAdministrative> valider(@PathVariable Long id) {
        return ResponseEntity.ok(demandeService.validerDemande(id));
    }

    @PutMapping("/{id}/refuser")
    public ResponseEntity<DemandeAdministrative> refuser(@PathVariable Long id) {
        return ResponseEntity.ok(demandeService.refuserDemande(id));
    }
}
