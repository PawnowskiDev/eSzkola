package pl.eszkola.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            return "admin/dashboard";
        } else {
            return "redirect:/login";
        }
    }
}
