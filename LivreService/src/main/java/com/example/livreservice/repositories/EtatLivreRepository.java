// src/main/java/com/example/livreservice/repositories/EtatLivreRepository.java
package com.example.livreservice.repositories;

import com.example.livreservice.entities.EtatLivre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtatLivreRepository extends JpaRepository<EtatLivre, Long> {
}