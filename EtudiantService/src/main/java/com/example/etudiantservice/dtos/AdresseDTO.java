// src/main/java/com/example/etudiantservice/dto/AdresseDTO.java
package com.example.etudiantservice.dtos;

public class AdresseDTO {
    private Long code;
    private String numAppart;
    private String rue;
    private String ville;
    private String codePostal;

    // getters & setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public String getNumAppart() { return numAppart; }
    public void setNumAppart(String numAppart) { this.numAppart = numAppart; }
    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
}