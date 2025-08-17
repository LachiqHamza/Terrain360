package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.Absence;
import com.terrain360.terrain360.entities.Employe;
import com.terrain360.terrain360.repositories.AbsenceRepository;
import com.terrain360.terrain360.repositories.EmployeRepository;
import com.terrain360.terrain360.services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AbsenceServiceImpl implements AbsenceService {

    @Autowired
    private  AbsenceRepository absenceRepository;
    @Autowired
    private final EmployeRepository employeRepository;

    public AbsenceServiceImpl(AbsenceRepository absenceRepository, EmployeRepository employeRepository) {
        this.absenceRepository = absenceRepository;
        this.employeRepository = employeRepository;
    }

    @Override
    public Absence addAbsence(Long employeId, Absence absence) {
        Employe employe = employeRepository.findById(employeId).orElse(null);
        if (employe != null) {
            absence.setEmploye(employe);
            return absenceRepository.save(absence);
        }
        return null;
    }

    @Override
    public List<Absence> getAbsencesByEmploye(Long employeId) {
        return absenceRepository.findAll().stream()
                .filter(a -> a.getEmploye().getId().equals(employeId))
                .toList();
    }
}
