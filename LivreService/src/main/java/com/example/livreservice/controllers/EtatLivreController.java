// src/main/java/com/example/livreservice/controllers/EtatLivreController.java
package com.example.livreservice.controllers;

import com.example.livreservice.dto.EtatLivreDTO;
import com.example.livreservice.services.EtatLivreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etats-l")
public class EtatLivreController {

    private final EtatLivreService etatLivreService;

    public EtatLivreController(EtatLivreService etatLivreService) {
        this.etatLivreService = etatLivreService;
    }

    @GetMapping
    public ResponseEntity<List<EtatLivreDTO>> getAll() {
        return ResponseEntity.ok(etatLivreService.getAllEtats());
    }

    @GetMapping("/{code}")
    public ResponseEntity<EtatLivreDTO> getById(@PathVariable Long code) {
        return ResponseEntity.ok(etatLivreService.getEtatLivre(code));
    }

    @PostMapping
    public ResponseEntity<EtatLivreDTO> create(@RequestBody EtatLivreDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(etatLivreService.addEtatLivre(dto));
    }
}