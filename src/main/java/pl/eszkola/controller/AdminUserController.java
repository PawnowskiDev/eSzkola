package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.MyUser;

import pl.eszkola.service.UserService;

@Controller
@RequestMapping("/admin/user")
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
        model.addAttribute("user", new MyUser());
        return "admin/user/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute MyUser myUser) {
        // tutaj metoda generowania randomowego hasła
        userService.addUser(myUser);
        return "redirect:/admin/user";
    }

    @GetMapping("/edit/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        MyUser myUser = userService.getUserById(userId);
        model.addAttribute("user", myUser);
        return "admin/user/edit";
    }

    @PostMapping("/edit/{userId}")
    public String editUser(@PathVariable Long userId, @ModelAttribute MyUser myUser) {
        userService.updateUser(userId, myUser);
        return "redirect:/admin/user";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/user";
    }
}
