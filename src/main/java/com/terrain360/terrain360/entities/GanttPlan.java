package com.terrain360.terrain360.entities;

import jakarta.persistence.*;

@Entity
public class GanttPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nombreEnqueteursParJour;
    private String planificationAuto; // JSON ou texte

    @OneToOne
    @JoinColumn(name = "etude_id")
    private Etude etude;
}
