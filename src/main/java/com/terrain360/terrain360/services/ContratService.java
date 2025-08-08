package com.terrain360.terrain360.services;

import com.terrain360.terrain360.entities.Contrat;

import java.util.List;

public interface ContratService {
    Contrat addContrat(Long employeId, Contrat contrat);
    List<Contrat> getContratsByEmploye(Long employeId);
}
