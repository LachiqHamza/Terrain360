package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.Superviseur;
import com.terrain360.terrain360.repositories.SuperviseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SuperviseurService {
    private final SuperviseurRepository superviseurRepository;

    @Autowired
    public SuperviseurService(SuperviseurRepository superviseurRepository) {
        this.superviseurRepository = superviseurRepository;
    }

    public List<Superviseur> getAllSuperviseurs() {
        return superviseurRepository.findAll();
    }

    public Optional<Superviseur> getSuperviseurById(Long id) {
        return superviseurRepository.findById(id);
    }

    public Superviseur saveSuperviseur(Superviseur superviseur) {
        // Set any additional properties specific to Superviseur here
        return superviseurRepository.save(superviseur);
    }

    public Superviseur updateSuperviseur(Long id, Superviseur updated) {
        return superviseurRepository.findById(id)
                .map(existing -> {
                    existing.setNom(updated.getNom());
                    existing.setPrenom(updated.getPrenom());
                    existing.setEmail(updated.getEmail());
                    existing.setTele(updated.getTele());
                    existing.setAdresse(updated.getAdresse());
                    existing.setPoste(updated.getPoste());
                    // Update any Superviseur-specific fields here
                    return superviseurRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Superviseur not found with id: " + id));
    }

    public void deleteSuperviseur(Long id) {
        superviseurRepository.deleteById(id);
    }
}