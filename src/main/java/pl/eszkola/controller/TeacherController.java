package pl.eszkola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        // logika
        return "teacher/dashboard";
    }


}
