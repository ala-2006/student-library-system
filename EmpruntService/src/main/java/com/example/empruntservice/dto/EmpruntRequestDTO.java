// file: src/main/java/com/example/empruntservice/dto/EmpruntRequestDTO.java
package com.example.empruntservice.dto;


import org.antlr.v4.runtime.misc.NotNull;

public class EmpruntRequestDTO {

    @NotNull
    private Long etudiantId;

    @NotNull
    private Long livreId;

    // getters & setters
    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public Long getLivreId() {
        return livreId;
    }

    public void setLivreId(Long livreId) {
        this.livreId = livreId;
    }
}