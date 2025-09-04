package com.terrain360.terrain360.DTO;

import java.time.LocalDate;

public class GanttTaskDTO {
    private Long id;
    private String name;
    private LocalDate start;
    private LocalDate end;
    private Integer progress;
    private String dependencies;
    private String supervisor;
    private String client;
    private Integer quotas;
    private Integer completedQuotas;
    private Integer requiredEnumerators;
    private Integer availableEnumerators;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getQuotas() {
        return quotas;
    }

    public void setQuotas(Integer quotas) {
        this.quotas = quotas;
    }

    public Integer getCompletedQuotas() {
        return completedQuotas;
    }

    public void setCompletedQuotas(Integer completedQuotas) {
        this.completedQuotas = completedQuotas;
    }

    public Integer getRequiredEnumerators() {
        return requiredEnumerators;
    }

    public void setRequiredEnumerators(Integer requiredEnumerators) {
        this.requiredEnumerators = requiredEnumerators;
    }

    public Integer getAvailableEnumerators() {
        return availableEnumerators;
    }

    public void setAvailableEnumerators(Integer availableEnumerators) {
        this.availableEnumerators = availableEnumerators;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
