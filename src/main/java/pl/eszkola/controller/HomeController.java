package pl.eszkola.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping
    public String eszkola (Model model) {
        // Pobierz informacje o zalogowanym użytkowniku
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Przekaż informacje do widoku
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/eszkola";
    }
}

