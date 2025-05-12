package it.giuseppe.form.controller;

import it.giuseppe.form.model.Utente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class UtenteController {

    @GetMapping("/form")
    public String getForm() {
        return "form";
    }

    @GetMapping("/form2")
    public String getForm2(@ModelAttribute Utente utente) {
        return "form2";
    }

    @PostMapping("/submitForm")
    public String postForm(@ModelAttribute Utente utente, Model model) {
        // Lista di errori
        List<String> errori = validaUtente(utente);

        // Se ci sono errori, aggiungili al modello e torna alla pagina del form
        if (!errori.isEmpty()) {
            model.addAttribute("errori", errori);
            return "form2"; // Torna al form per visualizzare gli errori
        }

        // Se non ci sono errori, mostra il risultato
        model.addAttribute("utente", utente);
        return "result";
    }

    private List<String> validaUtente(Utente utente) {
        List<String> errori = new ArrayList<>();

        // 2. Controllo dei Nomi Bannati
        List<String> nomiBannati = Arrays.asList("admin", "root", "administrator");
        if (nomiBannati.contains(utente.getNome().toLowerCase())) {
            errori.add("Nome utente non valido. Per favore, scegli un altro nome.");
        }

        // 3. Verifica della Lunghezza del Nome
        if (utente.getNome().length() < 3 || utente.getNome().length() > 20) {
            errori.add("Il nome utente deve essere tra 3 e 20 caratteri.");
        }

        // 4. Validazione della Formattazione dell'Email
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (utente.getEmail() == null || !pattern.matcher(utente.getEmail()).matches()) {
            errori.add("Email non valida. Assicurati che sia nel formato corretto.");
        }

        // 5. Controllo del Numero di Telefono
        String phoneRegex = "^[0-9]{10}$"; // Esempio di formato (10 cifre)
        if (utente.getNumeroDiTelefono() == null || !utente.getNumeroDiTelefono().matches(phoneRegex)) {
            errori.add("Numero di telefono non valido. Deve contenere 10 cifre.");
        }

        // 6. Confronto delle Password
        if (utente.getPassword() == null || !utente.getPassword().equals(utente.getConfermaPassword())) {
            errori.add("Le password non corrispondono.");
        }

        return errori;
    }
}
