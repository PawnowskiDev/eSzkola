package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.User;
import pl.eszkola.service.AdminService;
import pl.eszkola.service.AdminServiceImpl;
import pl.eszkola.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/user/list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        // tutaj metoda generowania randomowego hasła
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping("/edit/{userId}")
    public String editUser(@PathVariable Long userId, @ModelAttribute User user) {
        userService.updateUser(userId, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }
}
