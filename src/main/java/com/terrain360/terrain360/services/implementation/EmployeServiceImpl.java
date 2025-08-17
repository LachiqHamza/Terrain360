package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.Employe;
import com.terrain360.terrain360.repositories.EmployeRepository;
import com.terrain360.terrain360.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeService {
    @Autowired
    private  EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    @Transactional
    public Employe saveEmploye(Employe employe) {

        if(employe.getId() != null) {
            throw new IllegalArgumentException("New employee cannot have an ID");
        }
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id).orElse(null);
    }

    @Override
    public Employe updateEmploye(Long id, Employe updated) {
        Optional<Employe> optional = employeRepository.findById(id);
        if (optional.isPresent()) {
            Employe e = optional.get();
            e.setNom(updated.getNom());
            e.setPrenom(updated.getPrenom());
            e.setEmail(updated.getEmail());
            e.setTele(updated.getTele());
            e.setPoste(updated.getPoste());
            e.setAdresse(updated.getAdresse());
            return employeRepository.save(e);
        }
        return null;
    }

    @Override
    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }
}
