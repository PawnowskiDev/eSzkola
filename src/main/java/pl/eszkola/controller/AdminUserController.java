package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.MyUser;

import pl.eszkola.service.AdminService;
import pl.eszkola.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    private final UserService userService;

    private final AdminService adminService;

    @Autowired
    public AdminUserController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }


    @GetMapping("/addUser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "admin/user/add";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute MyUser myUser, Model model) {
        userService.saveUser(myUser);
        model.addAttribute("user", new MyUser());
        return "admin/user/add";
    }

    // todo nie aktualizuje danych

    @GetMapping("/editUser/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        MyUser myUser = userService.getUserById(userId);
        model.addAttribute("user", myUser);
        model.addAttribute("userId", userId);
        return "admin/user/update";
    }

    @PostMapping("/editUser/{userId}")
    public String editUser(@PathVariable Long userId, @ModelAttribute MyUser myUser) {
        userService.updateUser(userId, myUser);
        return "redirect:/admin/user/search";
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser (@PathVariable Long userId, Model model) {
        try {
            MyUser myUser = userService.getUserById(userId);
            model.addAttribute("user", myUser);
            return "admin/user/delete";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/user/delete";
        }
    }

    @PostMapping("/deleteUser/{userID}")
    public String deleteUserForm(@RequestParam Long userId) {
        try {
            userService.deleteUser(userId);
            return "redirect:/admin/user/search";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/user/search";
        }
    }

//    @GetMapping("/deleteUser/{userId}")
//    public String deleteUser (@PathVariable Long userId, Model model) {
//        MyUser myUser = userService.getUserById(userId);
//        model.addAttribute("user", myUser);
//        return "admin/user/delete";
//    }
//
//    @PostMapping("/deleteUser/{userId}")
//    public String deleteUserForm(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//        return "redirect:/admin/user/search";
//    }

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
