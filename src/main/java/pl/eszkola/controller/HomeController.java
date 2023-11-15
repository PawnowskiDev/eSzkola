package pl.eszkola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/eszkola/")
    public String home() {
        return "index";
    }
}
