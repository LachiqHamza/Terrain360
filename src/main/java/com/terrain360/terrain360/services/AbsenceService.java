package com.terrain360.terrain360.services;

import com.terrain360.terrain360.entities.Absence;

import java.util.List;

public interface AbsenceService {
    Absence addAbsence(Long employeId, Absence absence);
    List<Absence> getAbsencesByEmploye(Long employeId);
}
