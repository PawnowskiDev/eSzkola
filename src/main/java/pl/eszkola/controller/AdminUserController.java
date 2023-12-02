package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.MyUser;

import pl.eszkola.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/user/list")
//    public String listUsers(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "admin/user/list";
//    }

    @GetMapping("/user/addUser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "admin/user/add";
    }

    @PostMapping("/user/addUser")
    public String addUser(@ModelAttribute MyUser myUser) {
        // tutaj metoda generowania randomowego hasła
        userService.saveUser(myUser);
        return "redirect:admin/user/add";
    }

    @GetMapping("/user/edit/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        MyUser myUser = userService.getUserById(userId);
        model.addAttribute("user", myUser);
        return "admin/user/update";
    }

    @PostMapping("/user/edit/{userId}")
    public String editUser(@PathVariable Long userId, @ModelAttribute MyUser myUser) {
        userService.updateUser(userId, myUser);
        return "redirect:admin/user/update";
    }

    @PostMapping("/user/deleteUser")
    public String deleteUser (@RequestParam Long userId) {
        userService.deleteUser(userId);
        return "admin/user/delete";
    }

    @GetMapping("/user/deleteUser")
    public String deleteUserForm() {
        return "redirect:admin/user/dashboard";
    }

    @GetMapping("/user/search")
    public String searchUsersForm() {
        return "admin/user/search";
    }

    @PostMapping("/user/search")
    public String searchUsers(@RequestParam String userType, @RequestParam String keyword, Model model) {
        List<MyUser> users = userService.getUsersByTypeAndKeyword(userType, keyword);
        model.addAttribute("users", users);
        return "admin/user/search";
    }

}
