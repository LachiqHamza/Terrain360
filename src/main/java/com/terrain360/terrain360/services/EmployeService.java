package com.terrain360.terrain360.services;


import com.terrain360.terrain360.DTO.CreateEmployeDTO;
import com.terrain360.terrain360.DTO.EmployeDTO;
import com.terrain360.terrain360.DTO.UpdateEmployeDTO;
import com.terrain360.terrain360.entities.Role;

import java.util.List;

public interface EmployeService {
    EmployeDTO saveEmploye(CreateEmployeDTO employeDTO);
    List<EmployeDTO> getAllEmployes();
    EmployeDTO getEmployeById(Long id);
    EmployeDTO updateEmploye(Long id, UpdateEmployeDTO employeDTO);
    void deleteEmploye(Long id);
    List<EmployeDTO> getEmployesByRole(Role role);
}