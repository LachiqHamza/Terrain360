package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.Employe;
import com.terrain360.terrain360.entities.Salaire;
import com.terrain360.terrain360.repositories.EmployeRepository;
import com.terrain360.terrain360.repositories.SalaireRepository;
import com.terrain360.terrain360.services.SalaireService;

import java.util.List;

public class SalaireServiceImpl implements SalaireService {

    private final SalaireRepository salaireRepository;
    private final EmployeRepository employeRepository;

    public SalaireServiceImpl(SalaireRepository salaireRepository, EmployeRepository employeRepository) {
        this.salaireRepository = salaireRepository;
        this.employeRepository = employeRepository;
    }

    @Override
    public Salaire addSalaire(Long employeId, Salaire salaire) {
        Employe employe = employeRepository.findById(employeId).orElse(null);
        if (employe != null) {
            salaire.setEmploye(employe);
            return salaireRepository.save(salaire);
        }
        return null;
    }

    @Override
    public List<Salaire> getSalairesByEmploye(Long employeId) {
        return salaireRepository.findAll().stream()
                .filter(s -> s.getEmploye().getId().equals(employeId))
                .toList();
    }
}
