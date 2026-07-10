// src/main/java/com/example/etudiantservice/services/EtudiantService.java
package com.example.etudiantservice.services;

import com.example.etudiantservice.dtos.EtudiantDTO;
import com.example.etudiantservice.dtos.EtudiantRequestDTO;
import com.example.etudiantservice.entities.Adresse;
import com.example.etudiantservice.entities.Etudiant;
import com.example.etudiantservice.events.EmpruntCreatedEventMessage;
import com.example.etudiantservice.exceptions.AgeException;
import com.example.etudiantservice.exceptions.EtudiantNotFoundException;
import com.example.etudiantservice.mappers.AdresseMapper;
import com.example.etudiantservice.mappers.EtudiantMapper;
import com.example.etudiantservice.repositories.AdresseRepository;
import com.example.etudiantservice.repositories.EtudiantRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EtudiantService {
    @KafkaListener(
            topics = "student-result-topic",
            properties = {
                    "spring.json.value.default.type=com.example.etudiantservice.events.EmpruntCreatedEventMessage"
            }
    )
    public void traiterEvenement(EmpruntCreatedEventMessage message) {
        System.out.println("Received Message: " + message);
    }

    private final EtudiantRepository etudiantRepository;
    private final AdresseRepository adresseRepository;
    private final EtudiantMapper etudiantMapper;
    private final AdresseMapper adresseMapper;

    public EtudiantService(EtudiantRepository etudiantRepository,
                           AdresseRepository adresseRepository,
                           EtudiantMapper etudiantMapper,
                           AdresseMapper adresseMapper) {
        this.etudiantRepository = etudiantRepository;
        this.adresseRepository = adresseRepository;
        this.etudiantMapper = etudiantMapper;
        this.adresseMapper = adresseMapper;
    }

    private void validateAge(LocalDate dateNaissance) {
        int age = Period.between(dateNaissance, LocalDate.now()).getYears();
        if (age < 18) {
            throw new AgeException("L'étudiant doit avoir au moins 18 ans");
        }
    }

    public EtudiantDTO addEtudiant(EtudiantRequestDTO request) {
        validateAge(request.getDateNaissance());

        Adresse adresse = null;
        if (request.getAdresse() != null) {
            adresse = adresseMapper.toEntity(request.getAdresse());
            adresse = adresseRepository.save(adresse);
        }

        Etudiant etudiant = etudiantMapper.toEntity(request, adresse);
        etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.toDTO(etudiant);
    }

    public List<EtudiantDTO> getAllEtudiants() {
        return etudiantRepository.findAll()
                .stream()
                .map(etudiantMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EtudiantDTO getEtudiant(Long code) {
        Etudiant etudiant = etudiantRepository.findById(code)
                .orElseThrow(() -> new EtudiantNotFoundException("Étudiant introuvable avec code: " + code));
        return etudiantMapper.toDTO(etudiant);
    }

    public void deleteEtudiant(Long code) {
        if (!etudiantRepository.existsById(code)) {
            throw new EtudiantNotFoundException("Étudiant introuvable");
        }
        etudiantRepository.deleteById(code);
    }

    public EtudiantDTO updateEtudiant(Long code, EtudiantRequestDTO request) {
        Etudiant existing = etudiantRepository.findById(code)
                .orElseThrow(() -> new EtudiantNotFoundException("Étudiant introuvable"));

        validateAge(request.getDateNaissance());

        // Update address if provided
        if (request.getAdresse() != null) {
            Adresse adresse;
            if (existing.getAdresse() != null) {
                adresse = existing.getAdresse();
                adresse.setNumAppart(request.getAdresse().getNumAppart());
                adresse.setRue(request.getAdresse().getRue());
                adresse.setVille(request.getAdresse().getVille());
                adresse.setCodePostal(request.getAdresse().getCodePostal());
            } else {
                adresse = adresseMapper.toEntity(request.getAdresse());
            }
            adresse = adresseRepository.save(adresse);
            existing.setAdresse(adresse);
        }

        existing.setNom(request.getNom());
        existing.setPrenom(request.getPrenom());
        existing.setDateNaissance(request.getDateNaissance());

        return etudiantMapper.toDTO(etudiantRepository.save(existing));
    }

    public List<EtudiantDTO> getEtudiantsByNom(String nom) {
        List<Etudiant> etudiants = etudiantRepository.findByNomContainingIgnoreCase(nom);
        if (etudiants.isEmpty()) {
            throw new EtudiantNotFoundException("Aucun étudiant trouvé avec le nom: " + nom);
        }
        return etudiants.stream()
                .map(etudiantMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Helper method for Emprunt-Service to increment borrowed count
    public void incrementNombreLivresEmpruntes(Long code) {
        Etudiant etudiant = etudiantRepository.findById(code)
                .orElseThrow(() -> new EtudiantNotFoundException("Étudiant introuvable"));
        etudiant.setNombreLivresEmpruntes(etudiant.getNombreLivresEmpruntes() + 1);
        etudiantRepository.save(etudiant);
    }

    public void decrementNombreLivresEmpruntes(Long code) {
        Etudiant etudiant = etudiantRepository.findById(code)
                .orElseThrow(() -> new EtudiantNotFoundException("Étudiant introuvable"));
        int current = etudiant.getNombreLivresEmpruntes();
        if (current > 0) {
            etudiant.setNombreLivresEmpruntes(current - 1);
            etudiantRepository.save(etudiant);
        }
    }


}