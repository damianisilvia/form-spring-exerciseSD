package it.giuseppe.form.controller;

import it.giuseppe.form.model.Utente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("utente", utente);
        return "result";
    }
}
