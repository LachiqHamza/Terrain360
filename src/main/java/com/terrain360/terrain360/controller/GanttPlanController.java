package com.terrain360.terrain360.controller;

import com.terrain360.terrain360.entities.GanttPlan;
import com.terrain360.terrain360.services.implementation.GanttPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gantt")
@CrossOrigin(origins = "http://localhost:5173")
public class GanttPlanController {
    @Autowired
    private GanttPlanService ganttPlanService;

    public GanttPlanController(GanttPlanService ganttPlanService) {
        this.ganttPlanService = ganttPlanService;
    }

    // GET all plans
    @GetMapping
    public List<GanttPlan> getAll() {
        return ganttPlanService.getAllPlans();
    }

    // GET plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<GanttPlan> getById(@PathVariable Long id) {
        GanttPlan plan = ganttPlanService.getPlanById(id);
        if (plan != null) {
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET plan by etude ID
    @GetMapping("/etude/{etudeId}")
    public ResponseEntity<GanttPlan> getByEtude(@PathVariable Long etudeId) {
        GanttPlan plan = ganttPlanService.getPlanByEtude(etudeId);
        if (plan != null) {
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST create new plan
    @PostMapping
    public ResponseEntity<GanttPlan> create(@RequestBody GanttPlan plan) {
        try {
            GanttPlan createdPlan = ganttPlanService.createPlan(plan);
            return ResponseEntity.ok(createdPlan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT update plan
    @PutMapping("/{id}")
    public ResponseEntity<GanttPlan> update(@PathVariable Long id, @RequestBody GanttPlan plan) {
        GanttPlan updatedPlan = ganttPlanService.updatePlan(id, plan);
        if (updatedPlan != null) {
            return ResponseEntity.ok(updatedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        GanttPlan plan = ganttPlanService.getPlanById(id);
        if (plan != null) {
            ganttPlanService.deletePlan(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET plans by status
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<GanttPlan>> getByStatus(@PathVariable String statut) {
        List<GanttPlan> plans = ganttPlanService.getPlansByStatus(statut);
        return ResponseEntity.ok(plans);
    }

    // GET statistics
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("activeStudies", ganttPlanService.getActiveStudiesCount());
        stats.put("atRiskStudies", ganttPlanService.getAtRiskStudiesCount());
        stats.put("delayedStudies", ganttPlanService.getDelayedStudiesCount());
        stats.put("totalEnumerators", ganttPlanService.getTotalEnumeratorsCount());
        stats.put("totalStudies", ganttPlanService.getAllPlans().size());

        return ResponseEntity.ok(stats);
    }

    // GET required enumerators for a plan
    @GetMapping("/{id}/enqueteurs-requis")
    public ResponseEntity<Map<String, Object>> getRequiredEnumerators(@PathVariable Long id) {
        GanttPlan plan = ganttPlanService.getPlanById(id);
        if (plan != null) {
            int required = ganttPlanService.calculateRequiredEnumerators(plan);
            Map<String, Object> response = new HashMap<>();
            response.put("requiredEnumerators", required);
            response.put("availableEnumerators", plan.getEnqueteursDisponibles());
            response.put("difference", required - (plan.getEnqueteursDisponibles() != null ? plan.getEnqueteursDisponibles() : 0));

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH update plan dates
    @PatchMapping("/{id}/dates")
    public ResponseEntity<GanttPlan> updateDates(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        GanttPlan updatedPlan = ganttPlanService.updatePlanDates(id, start, end);
        if (updatedPlan != null) {
            return ResponseEntity.ok(updatedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH update progress
    @PatchMapping("/{id}/progress")
    public ResponseEntity<GanttPlan> updateProgress(
            @PathVariable Long id,
            @RequestParam Integer completedQuotas) {

        GanttPlan updatedPlan = ganttPlanService.updateProgress(id, completedQuotas);
        if (updatedPlan != null) {
            return ResponseEntity.ok(updatedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET all plans for Gantt chart (formatted for frontend)
    @GetMapping("/chart-data")
    public ResponseEntity<List<Map<String, Object>>> getChartData() {
        List<GanttPlan> plans = ganttPlanService.getAllPlans();
        List<Map<String, Object>> chartData = plans.stream().map(plan -> {
            Map<String, Object> task = new HashMap<>();
            task.put("id", plan.getId());
            task.put("name", plan.getEtude() != null ? plan.getEtude().getNom() : "Sans nom");
            task.put("start", plan.getDateDebut());
            task.put("end", plan.getDateFin());
            task.put("progress", plan.getProgres() != null ? plan.getProgres() : 0);
            task.put("dependencies", "");
            task.put("supervisor", plan.getSuperviseur());
            task.put("client", plan.getClient());
            task.put("quotas", plan.getQuotas());
            task.put("completedQuotas", plan.getQuotasCompletes());
            task.put("requiredEnumerators", plan.getNombreEnqueteursParJour());
            task.put("availableEnumerators", plan.getEnqueteursDisponibles());
            task.put("status", plan.getStatut());

            return task;
        }).toList();

        return ResponseEntity.ok(chartData);
    }
}