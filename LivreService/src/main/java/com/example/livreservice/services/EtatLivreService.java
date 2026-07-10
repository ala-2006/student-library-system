package com.example.livreservice.services;

import com.example.livreservice.dto.EtatLivreDTO;
import com.example.livreservice.entities.EtatLivre;
import com.example.livreservice.exceptions.BusinessException;
import com.example.livreservice.mappers.EtatLivreMapper;
import com.example.livreservice.repositories.EtatLivreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EtatLivreService {

    private final EtatLivreRepository etatLivreRepository;
    private final EtatLivreMapper etatLivreMapper;

    public EtatLivreService(EtatLivreRepository etatLivreRepository,
                            EtatLivreMapper etatLivreMapper) {
        this.etatLivreRepository = etatLivreRepository;
        this.etatLivreMapper = etatLivreMapper;
    }

    public EtatLivreDTO addEtatLivre(EtatLivreDTO dto) {
        EtatLivre etat = etatLivreMapper.toEntity(dto);
        etat = etatLivreRepository.save(etat);
        return etatLivreMapper.toDTO(etat);
    }

    public List<EtatLivreDTO> getAllEtats() {
        return etatLivreRepository.findAll()
                .stream()
                .map(etatLivreMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ ADD THIS MISSING METHOD
    public EtatLivreDTO getEtatLivre(Long code) {
        EtatLivre etat = etatLivreRepository.findById(code)
                .orElseThrow(() -> new BusinessException("État de livre introuvable avec code: " + code));
        return etatLivreMapper.toDTO(etat);
    }
}