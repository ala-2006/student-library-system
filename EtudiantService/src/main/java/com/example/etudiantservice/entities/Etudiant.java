// src/main/java/com/example/etudiantservice/entities/Etudiant.java
package com.example.etudiantservice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Integer nombreLivresEmpruntes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    public Etudiant() {
        this.nombreLivresEmpruntes = 0;
    }

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
    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
}