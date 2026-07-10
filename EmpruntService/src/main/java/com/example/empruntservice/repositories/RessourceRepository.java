// file: src/main/java/com/example/empruntservice/repositories/RessourceRepository.java
package com.example.empruntservice.repositories;

import com.example.empruntservice.entities.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
}