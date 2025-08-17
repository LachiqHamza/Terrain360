package com.terrain360.terrain360.controller;

import com.terrain360.terrain360.entities.Employe;
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
    private  EmployeServiceImpl employeService;

    public EmployeController() {
        employeService = null;
    }

    @Operation(summary = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee created successfully",
                    content = @Content(schema = @Schema(implementation = Employe.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Employe> createEmployee(@RequestBody Employe employe) {
        Employe savedEmploye = employeService.saveEmploye(employe);
        return ResponseEntity.ok(savedEmploye);
    }
    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<List<Employe>> getAllEmployees() {
        List<Employe> employees = employeService.getAllEmployes();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeeById(@PathVariable Long id) {
        Employe employe = employeService.getEmployeById(id);
        return employe != null ? ResponseEntity.ok(employe) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmployee(@PathVariable Long id, @RequestBody Employe updated) {
        Employe employe = employeService.updateEmploye(id, updated);
        return employe != null ? ResponseEntity.ok(employe) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeService.deleteEmploye(id);
        return ResponseEntity.noContent().build();
    }
}
