package com.example.livreservice.mappers;

import com.example.livreservice.dto.EtatLivreDTO;
import com.example.livreservice.entities.EtatLivre;
import org.springframework.stereotype.Component;

@Component
public class EtatLivreMapper {

    public EtatLivreDTO toDTO(EtatLivre etat) {
        if (etat == null) return null;
        EtatLivreDTO dto = new EtatLivreDTO();
        dto.setCode(etat.getCode());
        dto.setDisponible(etat.getDisponible());
        dto.setEmprunte(etat.getEmprunte());
        return dto;
    }

    // ⚠️ CRITICAL: For CREATE, do NOT set the ID
    public EtatLivre toEntity(EtatLivreDTO dto) {
        if (dto == null) return null;
        EtatLivre etat = new EtatLivre();

        etat.setDisponible(dto.getDisponible());
        etat.setEmprunte(dto.getEmprunte());
        return etat;
    }
}