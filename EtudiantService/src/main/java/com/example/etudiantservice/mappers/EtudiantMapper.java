// src/main/java/com/example/etudiantservice/mappers/EtudiantMapper.java
package com.example.etudiantservice.mappers;

import com.example.etudiantservice.dtos.EtudiantDTO;
import com.example.etudiantservice.dtos.EtudiantRequestDTO;
import com.example.etudiantservice.entities.Adresse;
import com.example.etudiantservice.entities.Etudiant;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    private final AdresseMapper adresseMapper;

    public EtudiantMapper(AdresseMapper adresseMapper) {
        this.adresseMapper = adresseMapper;
    }

    public EtudiantDTO toDTO(Etudiant etudiant) {
        if (etudiant == null) return null;
        EtudiantDTO dto = new EtudiantDTO();
        dto.setCode(etudiant.getCode());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setDateNaissance(etudiant.getDateNaissance());
        dto.setNombreLivresEmpruntes(etudiant.getNombreLivresEmpruntes());
        dto.setAdresse(adresseMapper.toDTO(etudiant.getAdresse()));
        return dto;
    }

    public Etudiant toEntity(EtudiantRequestDTO dto, Adresse adresse) {
        if (dto == null) return null;
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setDateNaissance(dto.getDateNaissance());
        etudiant.setNombreLivresEmpruntes(0);
        etudiant.setAdresse(adresse);
        return etudiant;
    }
}