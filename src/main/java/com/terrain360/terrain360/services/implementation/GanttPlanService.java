package com.terrain360.terrain360.services.implementation;

import com.terrain360.terrain360.entities.GanttPlan;
import com.terrain360.terrain360.repositories.GanttPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GanttPlanService {
    @Autowired
    private GanttPlanRepository ganttPlanRepository;

    public GanttPlanService(GanttPlanRepository ganttPlanRepository) {
        this.ganttPlanRepository = ganttPlanRepository;
    }

    public List<GanttPlan> getAllPlans() {
        return ganttPlanRepository.findAll();
    }

    public GanttPlan getPlanById(Long id) {
        return ganttPlanRepository.findById(id).orElse(null);
    }

    public GanttPlan getPlanByEtude(Long etudeId) {
        return ganttPlanRepository.findByEtudeId(etudeId);
    }

    public GanttPlan createPlan(GanttPlan plan) {
        // Auto-calculate progress if not provided
        if (plan.getProgres() == null && plan.getQuotas() != null && plan.getQuotasCompletes() != null) {
            plan.setProgres((int) ((double) plan.getQuotasCompletes() / plan.getQuotas() * 100));
        }

        // Auto-calculate status before saving
        plan.setStatut(calculateStatus(plan));

        // Auto-calculate required enumerators if not provided
        if (plan.getNombreEnqueteursParJour() == 0) {
            plan.setNombreEnqueteursParJour(calculateRequiredEnumerators(plan));
        }

        return ganttPlanRepository.save(plan);
    }

    public GanttPlan updatePlan(Long id, GanttPlan plan) {
        return ganttPlanRepository.findById(id).map(existing -> {
            existing.setNombreEnqueteursParJour(plan.getNombreEnqueteursParJour());
            existing.setPlanificationAuto(plan.getPlanificationAuto());
            existing.setEtude(plan.getEtude());
            existing.setQuotas(plan.getQuotas());
            existing.setQuotasCompletes(plan.getQuotasCompletes());
            existing.setDateDebut(plan.getDateDebut());
            existing.setDateFin(plan.getDateFin());
            existing.setProgres(plan.getProgres());
            existing.setClient(plan.getClient());
            existing.setSuperviseur(plan.getSuperviseur());
            existing.setEnqueteursDisponibles(plan.getEnqueteursDisponibles());

            // Recalculate progress if quotas are updated
            if (plan.getQuotas() != null && plan.getQuotasCompletes() != null) {
                existing.setProgres((int) ((double) plan.getQuotasCompletes() / plan.getQuotas() * 100));
            }

            // Recalculate status on update
            existing.setStatut(calculateStatus(existing));

            return ganttPlanRepository.save(existing);
        }).orElse(null);
    }

    public void deletePlan(Long id) {
        ganttPlanRepository.deleteById(id);
    }

    // New service methods for enhanced functionality
    public List<GanttPlan> getPlansByStatus(String statut) {
        return ganttPlanRepository.findByStatut(statut);
    }

    public Long getActiveStudiesCount() {
        return ganttPlanRepository.countActiveStudies();
    }

    public Long getAtRiskStudiesCount() {
        return ganttPlanRepository.countAtRiskStudies();
    }

    public Long getDelayedStudiesCount() {
        return ganttPlanRepository.countDelayedStudies();
    }

    public Long getTotalEnumeratorsCount() {
        return ganttPlanRepository.countTotalEnumerators();
    }

    public int calculateRequiredEnumerators(GanttPlan plan) {
        if (plan.getDateDebut() == null || plan.getDateFin() == null || plan.getQuotas() == null) {
            return 0;
        }

        long days = java.time.temporal.ChronoUnit.DAYS.between(plan.getDateDebut(), plan.getDateFin());
        if (days <= 0) return 0;

        // Assuming average productivity of 30 surveys per enumerator per day
        int avgProductivity = 30;
        return (int) Math.ceil((double) plan.getQuotas() / (days * avgProductivity));
    }

    private String calculateStatus(GanttPlan plan) {
        if (plan.getDateDebut() == null || plan.getDateFin() == null || plan.getProgres() == null) {
            return "unknown";
        }

        LocalDate now = LocalDate.now();

        // Check if study hasn't started yet
        if (now.isBefore(plan.getDateDebut())) {
            return "not_started";
        }

        // Check if study is completed
        if (now.isAfter(plan.getDateFin())) {
            return plan.getProgres() >= 100 ? "completed" : "delayed";
        }

        long totalDays = java.time.temporal.ChronoUnit.DAYS.between(plan.getDateDebut(), plan.getDateFin());
        long daysPassed = java.time.temporal.ChronoUnit.DAYS.between(plan.getDateDebut(), now);

        if (daysPassed <= 0) return "not_started";
        if (daysPassed >= totalDays) return "completed";

        double expectedProgress = (double) daysPassed / totalDays * 100;

        if (plan.getProgres() >= expectedProgress + 10) return "on_track";
        if (plan.getProgres() >= expectedProgress - 10) return "at_risk";
        return "delayed";
    }

    public GanttPlan updatePlanDates(Long id, LocalDate start, LocalDate end) {
        return ganttPlanRepository.findById(id).map(existing -> {
            existing.setDateDebut(start);
            existing.setDateFin(end);

            // Recalculate status after date change
            existing.setStatut(calculateStatus(existing));

            return ganttPlanRepository.save(existing);
        }).orElse(null);
    }

    public GanttPlan updateProgress(Long id, Integer completedQuotas) {
        return ganttPlanRepository.findById(id).map(existing -> {
            existing.setQuotasCompletes(completedQuotas);

            // Recalculate progress
            if (existing.getQuotas() != null && existing.getQuotas() > 0) {
                existing.setProgres((int) ((double) completedQuotas / existing.getQuotas() * 100));
            }

            // Recalculate status
            existing.setStatut(calculateStatus(existing));

            return ganttPlanRepository.save(existing);
        }).orElse(null);
    }
}