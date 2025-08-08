package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.DemandeAdministrative;
import com.terrain360.terrain360.entities.Employe;
import com.terrain360.terrain360.repositories.DemandeAdministrativeRepository;
import com.terrain360.terrain360.repositories.EmployeRepository;
import com.terrain360.terrain360.services.DemandeAdministrativeService;

import java.util.List;

public class DemandeAdministrativeServiceImpl implements DemandeAdministrativeService {

    private final DemandeAdministrativeRepository repository;
    private final EmployeRepository employeRepository;

    public DemandeAdministrativeServiceImpl(DemandeAdministrativeRepository repository, EmployeRepository employeRepository) {
        this.repository = repository;
        this.employeRepository = employeRepository;
    }

    @Override
    public DemandeAdministrative addDemande(Long employeId, DemandeAdministrative demande) {
        Employe employe = employeRepository.findById(employeId).orElse(null);
        if (employe != null) {
            demande.setEmploye(employe);
            demande.setStatut("EN_ATTENTE");
            return repository.save(demande);
        }
        return null;
    }

    @Override
    public List<DemandeAdministrative> getDemandesByEmploye(Long employeId) {
        return repository.findAll().stream()
                .filter(d -> d.getEmploye().getId().equals(employeId))
                .toList();
    }

    @Override
    public DemandeAdministrative validerDemande(Long id) {
        DemandeAdministrative demande = repository.findById(id).orElse(null);
        if (demande != null) {
            demande.setStatut("VALIDEE");
            return repository.save(demande);
        }
        return null;
    }

    @Override
    public DemandeAdministrative refuserDemande(Long id) {
        DemandeAdministrative demande = repository.findById(id).orElse(null);
        if (demande != null) {
            demande.setStatut("REFUSEE");
            return repository.save(demande);
        }
        return null;
    }
}
