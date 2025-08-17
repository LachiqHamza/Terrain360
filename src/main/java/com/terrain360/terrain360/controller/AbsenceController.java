package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.Absence;
import com.terrain360.terrain360.services.implementation.AbsenceServiceImpl;
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
@RequestMapping("/absences")
@Tag(name = "Absence Management", description = "Operations related to employee absences")
public class AbsenceController {

    @Autowired
    private  AbsenceServiceImpl absenceService;


    @Operation(summary = "Add an absence for an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Absence added successfully",
                    content = @Content(schema = @Schema(implementation = Absence.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PostMapping("/employee/{employeId}")
    public ResponseEntity<Absence> addAbsence(@PathVariable Long employeId, @RequestBody Absence absence) {
        Absence savedAbsence = absenceService.addAbsence(employeId, absence);
        return savedAbsence != null ? ResponseEntity.ok(savedAbsence) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all absences for an employee")
    @ApiResponse(responseCode = "200", description = "List of absences retrieved successfully",
            content = @Content(schema = @Schema(implementation = Absence.class)))
    @GetMapping("/employee/{employeId}")

    public ResponseEntity<List<Absence>> getAbsencesByEmployee(@PathVariable Long employeId) {
        List<Absence> absences = absenceService.getAbsencesByEmploye(employeId);
        return ResponseEntity.ok(absences);
    }

}
