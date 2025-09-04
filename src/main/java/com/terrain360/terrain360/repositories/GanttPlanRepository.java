package com.terrain360.terrain360.repositories;

import com.terrain360.terrain360.entities.GanttPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GanttPlanRepository extends JpaRepository<GanttPlan, Long> {
    GanttPlan findByEtudeId(Long etudeId);

    // Find plans by status
    List<GanttPlan> findByStatut(String statut);

    // Count active studies (not completed and end date not passed)
    @Query("SELECT COUNT(g) FROM GanttPlan g WHERE g.dateFin >= CURRENT_DATE AND g.progres < 100")
    Long countActiveStudies();

    // Count at-risk studies
    @Query("SELECT COUNT(g) FROM GanttPlan g WHERE g.statut = 'at_risk'")
    Long countAtRiskStudies();

    // Count delayed studies
    @Query("SELECT COUNT(g) FROM GanttPlan g WHERE g.statut = 'delayed'")
    Long countDelayedStudies();

    // Count total enumerators (sum of available enumerators)
    @Query("SELECT COALESCE(SUM(g.enqueteursDisponibles), 0) FROM GanttPlan g")
    Long countTotalEnumerators();

    // Find plans that need attention (at risk or delayed)
    @Query("SELECT g FROM GanttPlan g WHERE g.statut IN ('at_risk', 'delayed') ORDER BY g.dateFin ASC")
    List<GanttPlan> findPlansNeedingAttention();
}