// src/main/java/com/example/etudiantservice/controllers/EtudiantController.java
package com.example.etudiantservice.controllers;

import com.example.etudiantservice.dtos.EtudiantDTO;
import com.example.etudiantservice.dtos.EtudiantRequestDTO;
import com.example.etudiantservice.services.EtudiantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping
    public ResponseEntity<List<EtudiantDTO>> getAll() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    @GetMapping("/{code}")
    public ResponseEntity<EtudiantDTO> getById(@PathVariable Long code) {
        return ResponseEntity.ok(etudiantService.getEtudiant(code));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EtudiantDTO>> searchByNom(@RequestParam String nom) {
        return ResponseEntity.ok(etudiantService.getEtudiantsByNom(nom));
    }

    @PostMapping
    public ResponseEntity<EtudiantDTO> create(@Valid @RequestBody EtudiantRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(etudiantService.addEtudiant(request));
    }

    @PutMapping("/{code}")
    public ResponseEntity<EtudiantDTO> update(@PathVariable Long code,
                                              @Valid @RequestBody EtudiantRequestDTO request) {
        return ResponseEntity.ok(etudiantService.updateEtudiant(code, request));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        etudiantService.deleteEtudiant(code);
        return ResponseEntity.noContent().build();
    }

    // Endpoints for Emprunt-Service to update borrowed count
    @PutMapping("/{code}/increment-borrowed")
    public ResponseEntity<Void> incrementBorrowed(@PathVariable Long code) {
        etudiantService.incrementNombreLivresEmpruntes(code);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{code}/decrement-borrowed")
    public ResponseEntity<Void> decrementBorrowed(@PathVariable Long code) {
        etudiantService.decrementNombreLivresEmpruntes(code);
        return ResponseEntity.ok().build();
    }
}