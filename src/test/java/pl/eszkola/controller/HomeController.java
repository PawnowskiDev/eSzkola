package pl.eszkola.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class HomeController {

    @RequestMapping("/eszkola/")
    public String eszkola(Model model) {
        // pobieranie informacji o zalogowanym uzytkowniku
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // przekazanie informacji do widoku
        model.addAttribute("username", username);
        return "index";


    }
}
