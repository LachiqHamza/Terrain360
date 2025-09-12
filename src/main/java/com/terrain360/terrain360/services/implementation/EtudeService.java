package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.DTO.CreateEtudeDTO;
import com.terrain360.terrain360.entities.Etude;
import com.terrain360.terrain360.entities.Employe;
import com.terrain360.terrain360.entities.Role;
import com.terrain360.terrain360.repositories.EtudeRepository;
import com.terrain360.terrain360.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudeService {

    @Autowired
    private EtudeRepository etudeRepository;

    @Autowired
    private EmployeRepository employeRepository;

    public EtudeService(EtudeRepository etudeRepository, EmployeRepository employeRepository) {
        this.etudeRepository = etudeRepository;
        this.employeRepository = employeRepository;
    }

    public List<Etude> getAllEtudes() {
        return etudeRepository.findAll();
    }

    public Optional<Etude> getEtudeById(Long id) {
        return etudeRepository.findById(id);
    }

    public Etude saveEtude(CreateEtudeDTO etudeDTO) {
        Etude etude = new Etude();
        etude.setNom(etudeDTO.getNom());
        etude.setDateDebut(etudeDTO.getDateDebut());
        etude.setDateFin(etudeDTO.getDateFin());
        etude.setObjectifQuotas(etudeDTO.getObjectifQuotas());

        // Validate and assign supervisor
        if (etudeDTO.getSuperviseurId() != null) {
            Employe superviseur = employeRepository.findById(etudeDTO.getSuperviseurId())
                    .orElseThrow(() -> new RuntimeException("Superviseur non trouvé avec l'ID: " + etudeDTO.getSuperviseurId()));

            if (superviseur.getRole() == null) {
                throw new RuntimeException("Le superviseur n'a pas de rôle assigné");
            }

            if (!superviseur.getRole().equals(Role.SUPERVISEUR)) {
                throw new IllegalArgumentException("Seuls les employés avec le rôle SUPERVISEUR peuvent être assignés comme superviseurs. " +
                        "L'employé " + superviseur.getNom() + " " + superviseur.getPrenom() + " a le rôle: " + superviseur.getRole());
            }

            etude.setSuperviseur(superviseur);
        } else {
            throw new IllegalArgumentException("Un superviseur doit être assigné à l'étude");
        }

        return etudeRepository.save(etude);
    }

    public Etude updateEtude(Long id, Etude updated) {
        return etudeRepository.findById(id)
                .map(existing -> {
                    existing.setNom(updated.getNom());
                    existing.setDateDebut(updated.getDateDebut());
                    existing.setDateFin(updated.getDateFin());
                    existing.setObjectifQuotas(updated.getObjectifQuotas());

                    // Update supervisor if provided
                    if (updated.getSuperviseur() != null && updated.getSuperviseur().getId() != null) {
                        Employe superviseur = employeRepository.findById(updated.getSuperviseur().getId())
                                .orElseThrow(() -> new RuntimeException("Superviseur non trouvé"));

                        if (superviseur.getRole() == null) {
                            throw new RuntimeException("Le superviseur n'a pas de rôle assigné");
                        }

                        if (!superviseur.getRole().equals(Role.SUPERVISEUR)) {
                            throw new IllegalArgumentException("Seuls les employés avec le rôle SUPERVISEUR peuvent être assignés comme superviseurs");
                        }

                        existing.setSuperviseur(superviseur);
                    }

                    return etudeRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Etude not found"));
    }

    public void deleteEtude(Long id) {
        etudeRepository.deleteById(id);
    }

    public Employe getSuperviseurByEtude(Long id) {
        return etudeRepository.findById(id)
                .map(Etude::getSuperviseur)
                .orElseThrow(() -> new RuntimeException("Etude not found"));
    }

    public Etude assignEnqueteurToEtude(Long etudeId, Long employeId) {
        Etude etude = etudeRepository.findById(etudeId)
                .orElseThrow(() -> new RuntimeException("Etude not found"));

        Employe enqueteur = employeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Validate that employee has ENQUETEUR role
        if (!enqueteur.getRole().equals(Role.ENQUETEUR)) {
            throw new IllegalArgumentException("Only employees with ENQUETEUR role can be assigned to studies");
        }

        // Add to enqueteurs list if not already present
        if (etude.getEnqueteurs().stream().noneMatch(enq -> enq.getId().equals(employeId))) {
            etude.getEnqueteurs().add(enqueteur);
            return etudeRepository.save(etude);
        }

        return etude; // Already assigned
    }

    public Etude removeEnqueteurFromEtude(Long etudeId, Long employeId) {
        Etude etude = etudeRepository.findById(etudeId)
                .orElseThrow(() -> new RuntimeException("Etude not found"));

        etude.getEnqueteurs().removeIf(enq -> enq.getId().equals(employeId));
        return etudeRepository.save(etude);
    }

    public List<Etude> getEtudesBySuperviseur(Long superviseurId) {
        return etudeRepository.findBySuperviseurId(superviseurId);
    }

    public List<Etude> getEtudesByEnqueteur(Long employeId) {
        return etudeRepository.findByEnqueteursId(employeId);
    }

    public List<Employe> getEnqueteursByEtude(Long etudeId) {
        return etudeRepository.findById(etudeId)
                .map(Etude::getEnqueteurs)
                .orElseThrow(() -> new RuntimeException("Etude not found"));
    }
}