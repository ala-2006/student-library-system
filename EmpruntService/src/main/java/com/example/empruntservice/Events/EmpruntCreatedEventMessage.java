package com.example.empruntservice.Events

public record EmpruntCreatedEventMessage(
        int empruntId,
        int emprunteurId,
        int ressourcesId,
){}