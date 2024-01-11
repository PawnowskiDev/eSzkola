package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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


    @GetMapping("/editUser/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        MyUser myUser = userService.getUserById(userId);
        model.addAttribute("user", myUser);
        model.addAttribute("userId", userId);
        return "admin/user/update";
    }

    @PostMapping("/editUser/{userId}")
    public String editUser(@PathVariable Long userId, @ModelAttribute MyUser myUser, Model model) {
       try {
           MyUser updatedUser = adminService.updateUser(userId, myUser);
           model.addAttribute("updatedUser", updatedUser);
           return "redirect:/admin/user/search";
       } catch (Exception e) {
           e.printStackTrace();
           return "redirect:/admin/user/search";
       }
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUserForm (@PathVariable Long userId, Model model) {
        try {
            MyUser user = userService.getUserById(userId);
            model.addAttribute("user", user);
            return "admin/user/delete";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/user/delete";
        }
    }

    // a tu deleteUser

    @PostMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(userId);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
            return "redirect:/admin/user/search";
        } catch (Exception e) {

            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete user");
            return "redirect:/admin/user/search";
        }
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
