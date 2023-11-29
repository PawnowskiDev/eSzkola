package pl.eszkola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parent")
public class ParentController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        //logika
        return "parent/dashboard";
    }
}
