package pl.eszkola.controller;

import jakarta.validation.Valid;
import org.hibernate.engine.jdbc.mutation.spi.BindingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.MyUser;

import pl.eszkola.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/addUser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "admin/user/add";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute MyUser myUser) {

        userService.saveUser(myUser);
        return "redirect:/admin/user/add";
    }

    @GetMapping("/editUser/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        MyUser myUser = userService.getUserById(userId);
        model.addAttribute("user", myUser);
        return "admin/user/update";
    }

    @PostMapping("/editUser/{userId}")
    public String editUser(@PathVariable Long userId, @ModelAttribute MyUser myUser) {
        userService.updateUser(userId, myUser);
        return "redirect:/admin/user/search";
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser (@PathVariable Long userId, Model model) {
        MyUser myUser = userService.getUserById(userId);
        model.addAttribute("user", myUser);
        return "admin/user/delete";
    }

    @PostMapping("/deleteUser/{userId}")
    public String deleteUserForm(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/user/search";
    }

    @GetMapping("/search")
    public String searchUsersForm() {
        return "admin/user/search";
    }

    @PostMapping("/search")
    public String searchUsers(@RequestParam String userType, @RequestParam(required = false) String keyword, Model model) {

        List<MyUser> users = userService.getUsersByTypeAndKeyword(userType, keyword);
        model.addAttribute("users", users);

        System.out.println("Wyszukani użytkownicy: " + users);

        return "admin/user/search";
    }

}
