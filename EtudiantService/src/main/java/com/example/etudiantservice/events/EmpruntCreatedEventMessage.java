package com.example.etudiantservice.events;

public record EmpruntCreatedEventMessage(
        int empruntId,
        int emprunteurId,
        int ressourcesId
) {
}


