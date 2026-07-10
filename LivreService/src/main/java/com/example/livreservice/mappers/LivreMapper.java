// src/main/java/com/example/livreservice/mappers/LivreMapper.java
package com.example.livreservice.mappers;

import com.example.livreservice.dto.LivreDTO;
import com.example.livreservice.dto.LivreRequestDTO;
import com.example.livreservice.entities.Livre;
import org.springframework.stereotype.Component;

@Component
public class LivreMapper {

    private final EtatLivreMapper etatLivreMapper;

    public LivreMapper(EtatLivreMapper etatLivreMapper) {
        this.etatLivreMapper = etatLivreMapper;
    }

    public LivreDTO toDTO(Livre livre) {
        if (livre == null) return null;
        LivreDTO dto = new LivreDTO();
        dto.setCode(livre.getCode());
        dto.setIsbn(livre.getIsbn());
        dto.setTitre(livre.getTitre());
        dto.setAuteur(livre.getAuteur());
        dto.setDatePublication(livre.getDatePublication());
        dto.setNombreCopies(livre.getNombreCopies());
        dto.setEtat(etatLivreMapper.toDTO(livre.getEtat()));
        return dto;
    }

    public Livre toEntity(LivreRequestDTO dto, com.example.livreservice.entities.EtatLivre etat) {
        if (dto == null) return null;
        Livre livre = new Livre();
        livre.setIsbn(dto.getIsbn());
        livre.setTitre(dto.getTitre());
        livre.setAuteur(dto.getAuteur());
        livre.setDatePublication(dto.getDatePublication());
        livre.setNombreCopies(dto.getNombreCopies());
        livre.setEtat(etat);
        return livre;
    }
}