// src/main/java/com/example/livreservice/entities/EtatLivre.java
package com.example.livreservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "etats_livre")
public class EtatLivre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private Boolean disponible;
    private Boolean emprunte;   // true if borrowed

    public EtatLivre() {}

    public EtatLivre(Boolean disponible, Boolean emprunte) {
        this.disponible = disponible;
        this.emprunte = emprunte;
    }

    // getters & setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
    public Boolean getEmprunte() { return emprunte; }
    public void setEmprunte(Boolean emprunte) { this.emprunte = emprunte; }
}