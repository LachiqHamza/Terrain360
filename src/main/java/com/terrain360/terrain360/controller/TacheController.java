package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.Etude;
import com.terrain360.terrain360.entities.Statut;
import com.terrain360.terrain360.entities.Tache;
import com.terrain360.terrain360.repositories.EtudeRepository;
import com.terrain360.terrain360.services.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
public class TacheController {
    @Autowired
     final TacheService tacheService;
    @Autowired
     final EtudeRepository etudeRepository;

    public TacheController(TacheService tacheService, EtudeRepository etudeRepository) {
        this.tacheService = tacheService;
        this.etudeRepository = etudeRepository;
    }

    @GetMapping("/etude/{etudeId}")
    public List<Tache> getTaches(@PathVariable Long etudeId) {
        return tacheService.getTachesByEtude(etudeId);
    }

    // RH only
    @PostMapping("/etude/{etudeId}")
    public ResponseEntity<Tache> createTache(@PathVariable Long etudeId, @RequestBody Tache tache) {
        Etude etude = etudeRepository.findById(etudeId)
                .orElseThrow(() -> new RuntimeException("Etude not found"));
        return ResponseEntity.ok(tacheService.createTache(etudeId, tache, etude));
    }

    // Enquêteur updates status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Tache> updateStatus(@PathVariable Long id, @RequestParam Statut statut) {
        return ResponseEntity.ok(tacheService.updateStatus(id, statut));
    }
}
