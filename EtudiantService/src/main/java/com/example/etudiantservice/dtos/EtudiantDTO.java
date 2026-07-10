// src/main/java/com/example/etudiantservice/dto/EtudiantDTO.java
package com.example.etudiantservice.dtos;

import java.time.LocalDate;

public class EtudiantDTO {
    private Long code;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Integer nombreLivresEmpruntes;
    private AdresseDTO adresse;

    // getters & setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public Integer getNombreLivresEmpruntes() { return nombreLivresEmpruntes; }
    public void setNombreLivresEmpruntes(Integer nombreLivresEmpruntes) { this.nombreLivresEmpruntes = nombreLivresEmpruntes; }
    public AdresseDTO getAdresse() { return adresse; }
    public void setAdresse(AdresseDTO adresse) { this.adresse = adresse; }
}