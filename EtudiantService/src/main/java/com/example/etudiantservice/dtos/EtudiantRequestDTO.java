// src/main/java/com/example/etudiantservice/dto/EtudiantRequestDTO.java
package com.example.etudiantservice.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public class EtudiantRequestDTO {
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotNull
    @Past
    private LocalDate dateNaissance;
    @Valid
    private AdresseDTO adresse;

    // getters & setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public AdresseDTO getAdresse() { return adresse; }
    public void setAdresse(AdresseDTO adresse) { this.adresse = adresse; }
}