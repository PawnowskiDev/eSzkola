package pl.eszkola.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.MyUser;
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

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Błędny login lub hasło");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String pesel, @RequestParam String password, Model model) {
        MyUser myUser = userService.getUserByPESEL(pesel);

        if (myUser != null && passwordEncoder.matches(password, myUser.getPassword())) {
            String redirectPath = determineRedirectPath(myUser);
            return "redirect:" + redirectPath;
        } else {
            model.addAttribute("error", "Błędny login lub hasło");
            return "login";
        }
    }

    private String determineRedirectPath(MyUser myUser) {
        String userType = String.valueOf(myUser.getUserType());

        return switch (userType) {
            case "ADMIN" -> "/admin/dashboard";
            case "TEACHER" -> "/teacher/dashboard";
            case "STUDENT" -> "/student/dashboard";
            case "PARENT" -> "/parent/dashboard";
            default -> "/login"; // Domyślna ścieżka dla innych użytkowników
        };
    }
}

