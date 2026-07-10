// src/main/java/com/example/etudiantservice/repositories/AdresseRepository.java
package com.example.etudiantservice.repositories;

import com.example.etudiantservice.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}