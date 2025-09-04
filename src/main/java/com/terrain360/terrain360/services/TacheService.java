package com.terrain360.terrain360.services;


import com.terrain360.terrain360.entities.Etude;
import com.terrain360.terrain360.entities.Statut;
import com.terrain360.terrain360.entities.Tache;
import com.terrain360.terrain360.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService {
    @Autowired
    private TacheRepository tacheRepository;
    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    public List<Tache> getTachesByEtude(Long etudeId) {
        return tacheRepository.findByEtudeId(etudeId);
    }

    public Tache createTache(Long etudeId, Tache tache, Etude etude) {
        tache.setEtude(etude);
        return tacheRepository.save(tache);
    }

    public Tache updateStatus(Long id, Statut statut) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        tache.setStatut(statut);
        return tacheRepository.save(tache);
    }
}
