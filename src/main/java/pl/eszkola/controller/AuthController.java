package pl.eszkola.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.UserType;
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
    public String showLoginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Błędny pesel lub hasło");
        }
        return "auth/login" ;
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String pesel, @RequestParam String password, Model model) {
        MyUser myUser = userService.getUserByPESEL(pesel);

        if (myUser != null) {
            if (passwordEncoder.matches(password, myUser.getPassword())) {
                String redirectPath = determineRedirectPath(myUser);
                return "redirect:" + redirectPath;
            } else {
                model.addAttribute("error", "Błędne hasło");
            }
        } else {
            model.addAttribute("error", "Użytkownik o podanym numerze PESEL nie istnieje");
        }

        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute MyUser myUser, Model model) {

        userService.validateUserFields(myUser);

        // Przykładowa walidacja
        if (userService.getUserByPESEL(myUser.getPesel()) != null) {
            model.addAttribute("error", "Użytkownik o podanym numerze PESEL już istnieje");
            return "auth/register";
        }

        // Pobierz wartość jako string z formularza
        String userTypeString = myUser.getUserType().toString();

        // Mapuj wartość string na enum UserType
        UserType selectedUserType = UserType.valueOf(userTypeString);

        //  enum w obiekcie MyUser
        myUser.setUserType(selectedUserType);


        // hashowanie hasła przed zapisaniem do bazy
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));

        // Zapisz użytkownika do bazy danych
        userService.saveUser(myUser);

        // Przekieruj na stronę logowania lub wyświetl komunikat o sukcesie
        return "redirect:/auth/login";
    }

    private String determineRedirectPath(MyUser myUser) {
        String userType = String.valueOf(myUser.getUserType());

        return switch (userType) {
            case "ADMIN" -> "/admin/dashboard";
            case "TEACHER" -> "/teacher/dashboard";
            case "STUDENT" -> "/student/dashboard";
            case "PARENT" -> "/parent/dashboard";
            default -> "auth/login";
        };
    }
}

