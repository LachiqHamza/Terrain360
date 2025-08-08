package com.terrain360.terrain360.services;

import com.terrain360.terrain360.entities.DemandeAdministrative;

import java.util.List;

public interface DemandeAdministrativeService {
    DemandeAdministrative addDemande(Long employeId, DemandeAdministrative demande);
    List<DemandeAdministrative> getDemandesByEmploye(Long employeId);
    DemandeAdministrative validerDemande(Long id);
    DemandeAdministrative refuserDemande(Long id);
}
