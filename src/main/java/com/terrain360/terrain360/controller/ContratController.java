package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.Contrat;
import com.terrain360.terrain360.services.implementation.ContratServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrats")
public class ContratController {
    private final ContratServiceImpl contratService;

    public ContratController() {
        this(null);
    }

    public ContratController(ContratServiceImpl contratService) {
        this.contratService = contratService;
    }

    @PostMapping("/employe/{employeId}")
    public ResponseEntity<Contrat> addContrat(@PathVariable Long employeId, @RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.addContrat(employeId, contrat));
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<Contrat>> getContrats(@PathVariable Long employeId) {
        return ResponseEntity.ok(contratService.getContratsByEmploye(employeId));
    }
}
