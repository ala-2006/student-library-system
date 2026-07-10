package com.example.livreservice.controllers;

import com.example.livreservice.dto.LivreDTO;
import com.example.livreservice.dto.LivreRequestDTO;
import com.example.livreservice.services.LivreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public ResponseEntity<List<LivreDTO>> getAll() {
        return ResponseEntity.ok(livreService.getAllLivres());
    }

    @GetMapping("/{code}")
    public ResponseEntity<LivreDTO> getById(@PathVariable Long code) {
        return ResponseEntity.ok(livreService.getLivre(code));
    }

    @PostMapping
    public ResponseEntity<LivreDTO> create(@Valid @RequestBody LivreRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livreService.addLivre(request));
    }

    @PutMapping("/{code}")
    public ResponseEntity<LivreDTO> update(@PathVariable Long code,
                                           @Valid @RequestBody LivreRequestDTO request) {
        return ResponseEntity.ok(livreService.updateLivre(code, request));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code) {
        livreService.deleteLivre(code);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{code}/emprunter")
    public ResponseEntity<Void> emprunter(@PathVariable Long code) {
        livreService.emprunterLivre(code);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{code}/retourner")
    public ResponseEntity<Void> retourner(@PathVariable Long code) {
        livreService.retournerLivre(code);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{code}/disponible")
    public ResponseEntity<Boolean> isDisponible(@PathVariable Long code) {
        return ResponseEntity.ok(livreService.isDisponible(code));
    }
}