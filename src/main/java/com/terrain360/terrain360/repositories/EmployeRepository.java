package com.terrain360.terrain360.repositories;


import com.terrain360.terrain360.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
