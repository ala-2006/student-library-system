// file: src/main/java/com/example/empruntservice/repositories/EmpruntRepository.java
package com.example.empruntservice.repositories;

import com.example.empruntservice.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByEmprunteurCode(Long etudiantId);
    List<Emprunt> findByRessourceCode(Long livreId);
    List<Emprunt> findByEtat(String etat);
}