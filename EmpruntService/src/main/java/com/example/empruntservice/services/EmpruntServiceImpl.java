package com.example.empruntservice.services;

import com.example.empruntservice.dto.EmpruntRequestDTO;
import com.example.empruntservice.dto.EmpruntResponseDTO;
import com.example.empruntservice.entities.Emprunt;
import com.example.empruntservice.entities.Emprunteur;
import com.example.empruntservice.entities.Ressource;
import com.example.empruntservice.exceptions.BusinessException;
import com.example.empruntservice.mappers.EmpruntMapper;
import com.example.empruntservice.repositories.EmpruntRepository;
import com.example.empruntservice.repositories.EmprunteurRepository;
import com.example.empruntservice.repositories.RessourceRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmpruntServiceImpl implements IEmpruntService {

    private final EmpruntRepository empruntRepository;
    private final EmprunteurRepository emprunteurRepository;
    private final RessourceRepository ressourceRepository;
    private final EmpruntMapper empruntMapper;
    private final KafkaTemplate<String, EmpruntCreatedEventMessage> kafkaTemplate;

    // ✅ Single correct constructor
    public EmpruntServiceImpl(EmpruntRepository empruntRepository,
                              EmprunteurRepository emprunteurRepository,
                              RessourceRepository ressourceRepository,
                              EmpruntMapper empruntMapper,
                              KafkaTemplate<String, EmpruntCreatedEventMessage> kafkaTemplate) {
        this.empruntRepository = empruntRepository;
        this.emprunteurRepository = emprunteurRepository;
        this.ressourceRepository = ressourceRepository;
        this.empruntMapper = empruntMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void createEmprunt(Emprunt emprunt) {
        emprunt.setEtat(Etat.EN_ATTENTE);

        // ✅ Save before sending event
        emprunt = empruntRepository.save(emprunt);

        kafkaTemplate.send(
                "student-topic",
                new EmpruntCreatedEventMessage(
                        emprunt.getId(),
                        emprunt.getEmprunteur().getId(),
                        emprunt.getRessource().getId(),
                        Etat.EN_ATTENTE));
    }

    @Override
    public EmpruntResponseDTO emprunterLivre(EmpruntRequestDTO request) {

        Emprunteur emprunteur = emprunteurRepository.findById(request.getEtudiantId())
                .orElseGet(() -> {
                    Emprunteur newEmprunteur = new Emprunteur();
                    newEmprunteur.setCode(request.getEtudiantId());
                    newEmprunteur.setNom("Inconnu");
                    newEmprunteur.setPrenom("Inconnu");
                    return emprunteurRepository.save(newEmprunteur);
                });

        Ressource ressource = ressourceRepository.findById(request.getLivreId())
                .orElseGet(() -> {
                    Ressource newRessource = new Ressource();
                    newRessource.setCode(request.getLivreId());
                    newRessource.setIsbn("Inconnu");
                    newRessource.setTitre("Inconnu");
                    return ressourceRepository.save(newRessource);
                });

        Emprunt emprunt = new Emprunt();
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setEtat("EN_COURS"); // (kept as-is to avoid unnecessary changes)
        emprunt.setEmprunteur(emprunteur);
        emprunt.setRessource(ressource);

        emprunt = empruntRepository.save(emprunt);

        return empruntMapper.toResponseDTO(emprunt);
    }

    @Override
    public EmpruntResponseDTO retournerLivre(Long empruntCode) {
        Emprunt emprunt = empruntRepository.findById(empruntCode)
                .orElseThrow(() -> new BusinessException("Emprunt non trouvé"));

        if ("RETOURNE".equals(emprunt.getEtat())) {
            throw new BusinessException("Ce livre a déjà été retourné");
        }

        emprunt.setDateRetour(LocalDate.now());
        emprunt.setEtat("RETOURNE");
        empruntRepository.save(emprunt);

        return empruntMapper.toResponseDTO(emprunt);
    }

    @Override
    public EmpruntResponseDTO getEmprunt(Long code) {
        Emprunt emprunt = empruntRepository.findById(code)
                .orElseThrow(() -> new BusinessException("Emprunt non trouvé"));
        return empruntMapper.toResponseDTO(emprunt);
    }

    @Override
    public List<EmpruntResponseDTO> getEmpruntsByEtudiant(Long etudiantId) {
        return empruntRepository.findByEmprunteurCode(etudiantId)
                .stream()
                .map(empruntMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpruntResponseDTO> getEmpruntsByLivre(Long livreId) {
        return empruntRepository.findByRessourceCode(livreId)
                .stream()
                .map(empruntMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}