package it.giuseppe.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Utente {
    private String nome;
    private String email;
    private String password; // Nuovo campo
    private String confermaPassword; // Nuovo campo
    private Date dataDiNascita; // Nuovo campo
    private String numeroDiTelefono; // Nuovo campo
}
