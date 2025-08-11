package com.terrain360.terrain360.controller;


import com.terrain360.terrain360.entities.Absence;
import com.terrain360.terrain360.services.implementation.AbsenceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {

    private final AbsenceServiceImpl absenceService;

    public AbsenceController() {
        this(null);
    }

    public AbsenceController(AbsenceServiceImpl absenceService) {
        this.absenceService = absenceService;
    }

    @PostMapping("/employe/{employeId}")
    public ResponseEntity<Absence> addAbsence(@PathVariable Long employeId, @RequestBody Absence absence) {
        return ResponseEntity.ok(absenceService.addAbsence(employeId, absence));
    }

    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<Absence>> getAbsences(@PathVariable Long employeId) {
        return ResponseEntity.ok(absenceService.getAbsencesByEmploye(employeId));
    }

}
