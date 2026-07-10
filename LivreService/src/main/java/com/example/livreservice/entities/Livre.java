// src/main/java/com/example/livreservice/entities/Livre.java
package com.example.livreservice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "livres")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(unique = true, nullable = false)
    private String isbn;

    private String titre;
    private String auteur;
    private LocalDate datePublication;
    private Integer nombreCopies;

    @ManyToOne
    @JoinColumn(name = "etat_livre_id")
    private EtatLivre etat;

    public Livre() {}

    // getters and setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public LocalDate getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDate datePublication) { this.datePublication = datePublication; }
    public Integer getNombreCopies() { return nombreCopies; }
    public void setNombreCopies(Integer nombreCopies) { this.nombreCopies = nombreCopies; }
    public EtatLivre getEtat() { return etat; }
    public void setEtat(EtatLivre etat) { this.etat = etat; }
}