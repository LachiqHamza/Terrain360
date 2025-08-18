package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.Enqueteur;
import com.terrain360.terrain360.repositories.EnqueteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnqueteurService {
    private final EnqueteurRepository enqueteurRepository;

    @Autowired
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
        // Set any additional properties specific to Enqueteur here
        return enqueteurRepository.save(enqueteur);
    }

    public Enqueteur updateEnqueteur(Long id, Enqueteur updated) {
        return enqueteurRepository.findById(id)
                .map(existing -> {
                    existing.setNom(updated.getNom());
                    existing.setPrenom(updated.getPrenom());
                    existing.setEmail(updated.getEmail());
                    existing.setTele(updated.getTele());
                    existing.setAdresse(updated.getAdresse());
                    existing.setPoste(updated.getPoste());
                    // Update any Enqueteur-specific fields here
                    return enqueteurRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Enqueteur not found with id: " + id));
    }

    public void deleteEnqueteur(Long id) {
        enqueteurRepository.deleteById(id);
    }
}