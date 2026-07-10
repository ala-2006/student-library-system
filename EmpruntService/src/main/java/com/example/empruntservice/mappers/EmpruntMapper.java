// file: src/main/java/com/example/empruntservice/mappers/EmpruntMapper.java
package com.example.empruntservice.mappers;

import com.example.empruntservice.dto.EmpruntResponseDTO;
import com.example.empruntservice.entities.Emprunt;
import org.springframework.stereotype.Component;

@Component
public class EmpruntMapper {

    public EmpruntResponseDTO toResponseDTO(Emprunt emprunt) {
        if (emprunt == null) return null;

        EmpruntResponseDTO dto = new EmpruntResponseDTO();
        dto.setCode(emprunt.getCode());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setDateRetour(emprunt.getDateRetour());
        dto.setEtat(emprunt.getEtat());

        if (emprunt.getEmprunteur() != null) {
            dto.setNomEtudiant(emprunt.getEmprunteur().getNom());
            dto.setPrenomEtudiant(emprunt.getEmprunteur().getPrenom());
        }

        if (emprunt.getRessource() != null) {
            dto.setIsbnLivre(emprunt.getRessource().getIsbn());
            dto.setTitreLivre(emprunt.getRessource().getTitre());
        }

        return dto;
    }
}