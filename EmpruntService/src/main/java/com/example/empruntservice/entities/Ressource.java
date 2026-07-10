// file: src/main/java/com/example/empruntservice/entities/Ressource.java
package com.example.empruntservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ressources")
public class Ressource {

    @Id
    private Long code;   // same as Livre.id from Livre-Service

    private String isbn;
    private String titre;

    public Ressource() {}

    public Ressource(Long code, String isbn, String titre) {
        this.code = code;
        this.isbn = isbn;
        this.titre = titre;
    }

    // getters & setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
}