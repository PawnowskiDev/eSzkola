package pl.eszkola.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping("/eszkola")
    public String eszkola (Model model) {
        return "/templates/index.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/eszkola";
    }
}

