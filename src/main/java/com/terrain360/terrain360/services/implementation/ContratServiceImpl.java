package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.Contrat;
import com.terrain360.terrain360.entities.Employe;
import com.terrain360.terrain360.repositories.ContratRepository;
import com.terrain360.terrain360.repositories.EmployeRepository;
import com.terrain360.terrain360.services.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContratServiceImpl implements ContratService {
    @Autowired
    private  ContratRepository contratRepository;
    @Autowired
    private  EmployeRepository employeRepository;

    public ContratServiceImpl(ContratRepository contratRepository, EmployeRepository employeRepository) {
        this.contratRepository = contratRepository;
        this.employeRepository = employeRepository;
    }

    @Override
    public Contrat addContrat(Long employeId, Contrat contrat) {
        Employe employe = employeRepository.findById(employeId).orElse(null);
        if (employe != null) {
            contrat.setEmploye(employe);
            return contratRepository.save(contrat);
        }
        return null;
    }

    @Override
    public List<Contrat> getContratsByEmploye(Long employeId) {
        return contratRepository.findAll().stream()
                .filter(c -> c.getEmploye().getId().equals(employeId))
                .toList();
    }
}
