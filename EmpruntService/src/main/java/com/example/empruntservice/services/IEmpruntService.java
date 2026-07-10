// file: src/main/java/com/example/empruntservice/services/IEmpruntService.java
package com.example.empruntservice.services;

import com.example.empruntservice.dto.EmpruntRequestDTO;
import com.example.empruntservice.dto.EmpruntResponseDTO;
import java.util.List;

public interface IEmpruntService {
    EmpruntResponseDTO emprunterLivre(EmpruntRequestDTO request);
    EmpruntResponseDTO retournerLivre(Long empruntCode);
    EmpruntResponseDTO getEmprunt(Long code);
    List<EmpruntResponseDTO> getEmpruntsByEtudiant(Long etudiantId);
    List<EmpruntResponseDTO> getEmpruntsByLivre(Long livreId);
}