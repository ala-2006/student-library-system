// src/main/java/com/example/livreservice/repositories/LivreRepository.java
package com.example.livreservice.repositories;

import com.example.livreservice.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    Optional<Livre> findByIsbn(String isbn);
    List<Livre> findByTitreContainingIgnoreCase(String titre);
}