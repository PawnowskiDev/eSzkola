package pl.eszkola.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.User;
import pl.eszkola.service.UserService;

@Controller
@RequestMapping("/login")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping({"/login"})
    public String showLoginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Błędny login lub hasło");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String pesel, @RequestParam String password, Model model) {
        User user = userService.getUserByPESEL(pesel);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String redirectPath = determineRedirectPath(user);
            return "redirect:" + redirectPath;
        } else {
            model.addAttribute("error", "Błędny login lub hasło");
            return "login";
        }
    }

    private String determineRedirectPath(User user) {
        String userType = user.getUserType();

        return switch (userType) {
            case "ADMIN" -> "/admin/dashboard";
            case "TEACHER" -> "/teacher/dashboard";
            case "STUDENT" -> "/student/dashboard";
            case "PARENT" -> "/parent/dashboard";
            default -> "/login"; // Domyślna ścieżka dla innych użytkowników
        };
    }
}

