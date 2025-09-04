package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.DTO.CreateEmployeDTO;
import com.terrain360.terrain360.DTO.EmployeDTO;
import com.terrain360.terrain360.DTO.UpdateEmployeDTO;
import com.terrain360.terrain360.entities.Role;
import com.terrain360.terrain360.services.implementation.EmployeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management", description = "Operations related to employees")
public class EmployeController {

    @Autowired
    private EmployeServiceImpl employeService;

    public EmployeController() {
    }

    @Operation(summary = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee created successfully",
                    content = @Content(schema = @Schema(implementation = EmployeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<EmployeDTO> createEmployee(@RequestBody CreateEmployeDTO employeDTO) {
        EmployeDTO savedEmploye = employeService.saveEmploye(employeDTO);
        return ResponseEntity.ok(savedEmploye);
    }

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all employees",
                    content = @Content(schema = @Schema(implementation = EmployeDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<EmployeDTO>> getAllEmployees() {
        List<EmployeDTO> employees = employeService.getAllEmployes();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found",
                    content = @Content(schema = @Schema(implementation = EmployeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeDTO employe = employeService.getEmployeById(id);
        return ResponseEntity.ok(employe);
    }

    @Operation(summary = "Get employees by role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees by role",
                    content = @Content(schema = @Schema(implementation = EmployeDTO.class)))
    })
    @GetMapping("/role/{role}")
    public ResponseEntity<List<EmployeDTO>> getEmployeesByRole(@PathVariable Role role) {
        List<EmployeDTO> employees = employeService.getEmployesByRole(role);
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Update an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully",
                    content = @Content(schema = @Schema(implementation = EmployeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeDTO> updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeDTO updatedDTO) {
        EmployeDTO employe = employeService.updateEmploye(id, updatedDTO);
        return ResponseEntity.ok(employe);
    }

    @Operation(summary = "Delete an employee (soft delete)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeService.deleteEmploye(id);
        return ResponseEntity.noContent().build();
    }
}