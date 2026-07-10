// file: src/main/java/com/example/empruntservice/controllers/EmpruntController.java
package com.example.empruntservice.controllers;

import com.example.empruntservice.dto.EmpruntRequestDTO;
import com.example.empruntservice.dto.EmpruntResponseDTO;
import com.example.empruntservice.services.IEmpruntService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private final IEmpruntService empruntService;

    public EmpruntController(IEmpruntService empruntService) {
        this.empruntService = empruntService;
    }
    @GetMapping("/test")
    public ResponseEntity<String> getByEtudiant() {
        return ResponseEntity.ok("Test works!");
    }

    @PostMapping
    public ResponseEntity<EmpruntResponseDTO> emprunter( @RequestBody EmpruntRequestDTO request) {
        return ResponseEntity.ok(empruntService.emprunterLivre(request));
    }

    @PutMapping("/{code}/retour")
    public ResponseEntity<EmpruntResponseDTO> retourner(@PathVariable Long code) {
        return ResponseEntity.ok(empruntService.retournerLivre(code));
    }

    @GetMapping("/{code}")
    public ResponseEntity<EmpruntResponseDTO> getEmprunt(@PathVariable Long code) {
        return ResponseEntity.ok(empruntService.getEmprunt(code));
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<EmpruntResponseDTO>> getByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(empruntService.getEmpruntsByEtudiant(etudiantId));
    }

    @GetMapping("/livre/{livreId}")
    public ResponseEntity<List<EmpruntResponseDTO>> getByLivre(@PathVariable Long livreId) {
        return ResponseEntity.ok(empruntService.getEmpruntsByLivre(livreId));
    }
}