package com.terrain360.terrain360.repositories;


import com.terrain360.terrain360.entities.Employe;
import com.terrain360.terrain360.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    List<Employe> findByRole(Role role);
    Optional<Employe> findByNomDeUtilisateur(String nomDeUtilisateur);
    List<Employe> findByActif(Boolean actif);
    List<Employe> findByDepartement(String departement);
    boolean existsByNomDeUtilisateur(String nomDeUtilisateur);
    boolean existsByEmail(String email);
}
