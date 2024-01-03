package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.Subject;
import pl.eszkola.model.UserSubject;
import pl.eszkola.repository.SubjectRepository;
import pl.eszkola.repository.UserRepository;
import pl.eszkola.service.AdminService;

@Controller
@RequestMapping("/admin/subject")
public class AdminSubjectController {

    private final AdminService adminService;

    @Autowired
    public AdminSubjectController(AdminService adminService) {
        this.adminService = adminService;

    }

    @GetMapping("/addSubject")
    public String showAddSubject(Model model) {
        model.addAttribute("subject", new Subject());
        return "admin/subject/addSubject";
    }

    @PostMapping("/addSubject")
    public String addSubject(@ModelAttribute Subject subject) {
        adminService.createSubject(subject.getSubjectName(), subject.getSubjectDescription());
        return "redirect:/admin/subject/addSubject";
    }

    @GetMapping("/assignUserToSubject")
    public String showUserToSubjectForm(Model model) {
        model.addAttribute("userSubject", new UserS);
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("subjects", adminService.getAllSubjects());
        return "admin/subject/assignUserToSubject";
    }

    @PostMapping("/assignUserToSubject")
    public String assignUserToSubject(@RequestParam Long userId, @RequestParam Long subjectId) {
        adminService.assignUserToSubject(userId, subjectId);
        return "redirect:/admin/subject/assignUserToSubject?success";
    }

}
