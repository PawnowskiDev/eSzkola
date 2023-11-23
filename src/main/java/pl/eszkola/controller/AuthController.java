package pl.eszkola.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.User;
import pl.eszkola.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String pesel, @RequestParam String password) {
        User user = userService.getUserByPESEL(pesel);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return "redirect:/eszkola";
        } else {
            return "redirect:/login?error";
        }
    }
}
