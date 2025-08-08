package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.Etude;
import com.terrain360.terrain360.repositories.EtudeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudeService {
    private final EtudeRepository etudeRepository;

    public EtudeService(EtudeRepository etudeRepository) {
        this.etudeRepository = etudeRepository;
    }

    public List<Etude> getAllEtudes() {
        return etudeRepository.findAll();
    }

    public Optional<Etude> getEtudeById(Long id) {
        return etudeRepository.findById(id);
    }

    public Etude saveEtude(Etude etude) {
        return etudeRepository.save(etude);
    }

    public Etude updateEtude(Long id, Etude updated) {
        return etudeRepository.findById(id)
                .map(existing -> {
                    existing.setNom(updated.getNom());
                    existing.setDateDebut(updated.getDateDebut());
                    existing.setDateFin(updated.getDateFin());
                    return etudeRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Etude not found"));
    }

    public void deleteEtude(Long id) {
        etudeRepository.deleteById(id);
    }
}
