// file: src/main/java/com/example/empruntservice/entities/Emprunteur.java
package com.example.empruntservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprunteurs")
public class Emprunteur {

    @Id
    private Long code;   // same as Etudiant.id from Etudiant-Service

    private String nom;
    private String prenom;

    public Emprunteur() {}

    public Emprunteur(Long code, String nom, String prenom) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
    }

    // getters & setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}