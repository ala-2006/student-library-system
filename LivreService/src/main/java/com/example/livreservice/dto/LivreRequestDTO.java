// src/main/java/com/example/livreservice/dto/LivreRequestDTO.java
package com.example.livreservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.time.LocalDate;

public class LivreRequestDTO {
    @NotBlank
    private String isbn;
    @NotBlank
    private String titre;
    private String auteur;
    private LocalDate datePublication;
    @Positive
    private Integer nombreCopies;
    @NotNull
    private Long etatLivreId;   // reference to existing EtatLivre or we can embed

    // getters & setters
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
    public Long getEtatLivreId() { return etatLivreId; }
    public void setEtatLivreId(Long etatLivreId) { this.etatLivreId = etatLivreId; }
}