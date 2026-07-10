// src/main/java/com/example/livreservice/dto/EtatLivreDTO.java
package com.example.livreservice.dto;

public class EtatLivreDTO {
    private Long code;
    private Boolean disponible;
    private Boolean emprunte;

    // getters & setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
    public Boolean getEmprunte() { return emprunte; }
    public void setEmprunte(Boolean emprunte) { this.emprunte = emprunte; }
}