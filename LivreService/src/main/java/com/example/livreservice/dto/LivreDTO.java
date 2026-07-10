// src/main/java/com/example/livreservice/dto/LivreDTO.java
package com.example.livreservice.dto;

import java.time.LocalDate;

public class LivreDTO {
    private Long code;
    private String isbn;
    private String titre;
    private String auteur;
    private LocalDate datePublication;
    private Integer nombreCopies;
    private EtatLivreDTO etat;

    // getters & setters
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
    public EtatLivreDTO getEtat() { return etat; }
    public void setEtat(EtatLivreDTO etat) { this.etat = etat; }
}