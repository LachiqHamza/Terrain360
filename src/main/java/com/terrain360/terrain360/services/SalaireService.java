package com.terrain360.terrain360.services;

import com.terrain360.terrain360.entities.Salaire;

import java.util.List;

public interface SalaireService {
    Salaire addSalaire(Long employeId, Salaire salaire);
    List<Salaire> getSalairesByEmploye(Long employeId);
}
