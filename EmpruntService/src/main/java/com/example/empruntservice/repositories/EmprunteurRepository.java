// file: src/main/java/com/example/empruntservice/repositories/EmprunteurRepository.java
package com.example.empruntservice.repositories;

import com.example.empruntservice.entities.Emprunteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprunteurRepository extends JpaRepository<Emprunteur, Long> {
}