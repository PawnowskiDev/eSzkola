package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.Subject;
import pl.eszkola.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminSubjectController {

    private final AdminService adminService;
    @Autowired
    public AdminSubjectController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/subject/addSubject")
    public String showAddSubject(Model model) {
        model.addAttribute("subject", new Subject());
        return "admin/subject/addSubject";
    }

    @PostMapping("/subject/addSubject")
    public String addSubject(@ModelAttribute Subject subject) {
        adminService.createSubject(subject.getSubjectName(), subject.getSubjectDescription());
        return "redirect:/admin/subject/addSubject";
    }

    @GetMapping("/subject/userToSubject")
    public String showUserToSubjectForm(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("subjects", adminService.getAllSubjects());
        return "admin/assign/userToSubject";
    }

    @PostMapping("/subject/userToSubject")
    public String assignUserToSubject(@RequestParam Long userId, @RequestParam Long subjectId) {
        adminService.assignUserToSubject(userId, subjectId);
        return "redirect:/admin/assign/userToSubject?success";
    }

}
