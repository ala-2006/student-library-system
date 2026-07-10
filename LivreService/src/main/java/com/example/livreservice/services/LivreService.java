package com.example.livreservice.services;

import com.example.livreservice.dto.LivreDTO;
import com.example.livreservice.dto.LivreRequestDTO;
import com.example.livreservice.entities.EtatLivre;
import com.example.livreservice.entities.Livre;
import com.example.livreservice.exceptions.BusinessException;
import com.example.livreservice.exceptions.LivreNotFoundException;
import com.example.livreservice.mappers.LivreMapper;
import com.example.livreservice.repositories.EtatLivreRepository;
import com.example.livreservice.repositories.LivreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LivreService {

    private final LivreRepository livreRepository;
    private final EtatLivreRepository etatLivreRepository;
    private final LivreMapper livreMapper;

    public LivreService(LivreRepository livreRepository,
                        EtatLivreRepository etatLivreRepository,
                        LivreMapper livreMapper) {
        this.livreRepository = livreRepository;
        this.etatLivreRepository = etatLivreRepository;
        this.livreMapper = livreMapper;
    }

    public LivreDTO addLivre(LivreRequestDTO request) {
        EtatLivre etat = etatLivreRepository.findById(request.getEtatLivreId())
                .orElseThrow(() -> new BusinessException("État de livre invalide"));
        Livre livre = livreMapper.toEntity(request, etat);
        livre = livreRepository.save(livre);
        return livreMapper.toDTO(livre);
    }

    public List<LivreDTO> getAllLivres() {
        return livreRepository.findAll()
                .stream()
                .map(livreMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LivreDTO getLivre(Long code) {
        Livre livre = livreRepository.findById(code)
                .orElseThrow(() -> new LivreNotFoundException("Livre introuvable avec code: " + code));
        return livreMapper.toDTO(livre);
    }

    public void deleteLivre(Long code) {
        if (!livreRepository.existsById(code)) {
            throw new LivreNotFoundException("Livre introuvable");
        }
        livreRepository.deleteById(code);
    }

    public LivreDTO updateLivre(Long code, LivreRequestDTO request) {
        Livre existing = livreRepository.findById(code)
                .orElseThrow(() -> new LivreNotFoundException("Livre introuvable"));
        EtatLivre newEtat = etatLivreRepository.findById(request.getEtatLivreId())
                .orElseThrow(() -> new BusinessException("État invalide"));

        existing.setIsbn(request.getIsbn());
        existing.setTitre(request.getTitre());
        existing.setAuteur(request.getAuteur());
        existing.setDatePublication(request.getDatePublication());
        existing.setNombreCopies(request.getNombreCopies());
        existing.setEtat(newEtat);

        return livreMapper.toDTO(livreRepository.save(existing));
    }

    public void emprunterLivre(Long livreCode) {
        Livre livre = livreRepository.findById(livreCode)
                .orElseThrow(() -> new LivreNotFoundException("Livre non trouvé"));
        if (livre.getNombreCopies() <= 0) {
            throw new BusinessException("Livre non disponible pour l'emprunt");
        }
        livre.setNombreCopies(livre.getNombreCopies() - 1);
        livreRepository.save(livre);
    }

    public void retournerLivre(Long livreCode) {
        Livre livre = livreRepository.findById(livreCode)
                .orElseThrow(() -> new LivreNotFoundException("Livre non trouvé"));
        livre.setNombreCopies(livre.getNombreCopies() + 1);
        livreRepository.save(livre);
    }

    public boolean isDisponible(Long livreCode) {
        Livre livre = livreRepository.findById(livreCode).orElse(null);
        if (livre == null) return false;
        return livre.getNombreCopies() > 0;
    }
}