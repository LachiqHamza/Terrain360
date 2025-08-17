package com.terrain360.terrain360.services.implementation;


import com.terrain360.terrain360.entities.Superviseur;
import com.terrain360.terrain360.repositories.SuperviseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperviseurService {
    @Autowired
    private  SuperviseurRepository superviseurRepository;

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
        return superviseurRepository.save(superviseur);
    }

    public Superviseur updateSuperviseur(Long id, Superviseur updated) {
        return superviseurRepository.findById(id)
                .map(existing -> {
                    existing.setNom(updated.getNom());
                    existing.setPrenom(updated.getPrenom());
                    return superviseurRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Superviseur not found"));
    }

    public void deleteSuperviseur(Long id) {
        superviseurRepository.deleteById(id);
    }
}
