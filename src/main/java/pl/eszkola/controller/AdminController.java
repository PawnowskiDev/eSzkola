package pl.eszkola.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            model.addAttribute("userId", userId);
            return "admin/dashboard";
        } else {
            return "redirect:/login";
        }
    }
}
