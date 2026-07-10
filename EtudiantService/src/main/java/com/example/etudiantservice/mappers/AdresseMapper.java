// src/main/java/com/example/etudiantservice/mappers/AdresseMapper.java
package com.example.etudiantservice.mappers;

import com.example.etudiantservice.dtos.AdresseDTO;
import com.example.etudiantservice.entities.Adresse;
import org.springframework.stereotype.Component;

@Component
public class AdresseMapper {

    public AdresseDTO toDTO(Adresse adresse) {
        if (adresse == null) return null;
        AdresseDTO dto = new AdresseDTO();
        dto.setCode(adresse.getCode());
        dto.setNumAppart(adresse.getNumAppart());
        dto.setRue(adresse.getRue());
        dto.setVille(adresse.getVille());
        dto.setCodePostal(adresse.getCodePostal());
        return dto;
    }

    public Adresse toEntity(AdresseDTO dto) {
        if (dto == null) return null;
        Adresse adresse = new Adresse();
        adresse.setCode(dto.getCode());
        adresse.setNumAppart(dto.getNumAppart());
        adresse.setRue(dto.getRue());
        adresse.setVille(dto.getVille());
        adresse.setCodePostal(dto.getCodePostal());
        return adresse;
    }
}