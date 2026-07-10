package com.example.livreservice.exceptions;

public class LivreNotFoundException extends RuntimeException {
    public LivreNotFoundException(String message) {
        super(message);
    }
}
