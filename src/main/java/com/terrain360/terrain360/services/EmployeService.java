package com.terrain360.terrain360.services;

import com.terrain360.terrain360.entities.Employe;

import java.util.List;

public interface EmployeService {
    Employe saveEmploye(Employe employe);
    List<Employe> getAllEmployes();
    Employe getEmployeById(Long id);
    Employe updateEmploye(Long id, Employe employe);
    void deleteEmploye(Long id);
}
