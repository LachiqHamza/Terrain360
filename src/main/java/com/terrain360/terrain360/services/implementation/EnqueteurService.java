package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.Enqueteur;
import com.terrain360.terrain360.repositories.EnqueteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnqueteurService {
    @Autowired
    private  EnqueteurRepository enqueteurRepository;

    public EnqueteurService(EnqueteurRepository enqueteurRepository) {
        this.enqueteurRepository = enqueteurRepository;
    }

    public List<Enqueteur> getAllEnqueteurs() {
        return enqueteurRepository.findAll();
    }

    public Optional<Enqueteur> getEnqueteurById(Long id) {
        return enqueteurRepository.findById(id);
    }

    public Enqueteur saveEnqueteur(Enqueteur enqueteur) {
        return enqueteurRepository.save(enqueteur);
    }

    public Enqueteur updateEnqueteur(Long id, Enqueteur updated) {
        return enqueteurRepository.findById(id)
                .map(existing -> {
                    existing.setNom(updated.getNom());
                    existing.setPrenom(updated.getPrenom());
                    return enqueteurRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Enqueteur not found"));
    }

    public void deleteEnqueteur(Long id) {
        enqueteurRepository.deleteById(id);
    }
}
