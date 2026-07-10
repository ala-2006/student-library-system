// file: src/main/java/com/example/empruntservice/entities/Emprunt.java
package com.example.empruntservice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprunts")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private String etat;   // "EN_COURS", "RETOURNE", "EN_RETARD"

    @ManyToOne
    @JoinColumn(name = "emprunteur_id")
    private Emprunteur emprunteur;

    @ManyToOne
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    public Emprunt() {}

    // getters & setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }
    public LocalDate getDateRetour() { return dateRetour; }
    public void setDateRetour(LocalDate dateRetour) { this.dateRetour = dateRetour; }
    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
    public Emprunteur getEmprunteur() { return emprunteur; }
    public void setEmprunteur(Emprunteur emprunteur) { this.emprunteur = emprunteur; }
    public Ressource getRessource() { return ressource; }
    public void setRessource(Ressource ressource) { this.ressource = ressource; }
}