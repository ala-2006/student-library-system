// src/main/java/com/example/etudiantservice/repositories/EtudiantRepository.java
package com.example.etudiantservice.repositories;

import com.example.etudiantservice.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByCode(Long code);
    List<Etudiant> findByNomContainingIgnoreCase(String nom);
}