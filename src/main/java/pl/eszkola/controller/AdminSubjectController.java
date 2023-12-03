package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.eszkola.model.Subject;
import pl.eszkola.model.TeacherSubject;
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
    @GetMapping("/subject/assignTeacherToSubject")
    public String showAssignTeacherToSubject(Model model) {
        model.addAttribute("teacherSubject", new TeacherSubject());
        model.addAttribute("subjects", adminService.getAllSubjects());
        model.addAttribute("teachers", adminService.getAllTeachers());
        return "admin/subject/assignTeacherToSubject";
    }

    @PostMapping("/subject/assignTeacherToSubject")
    public String assignTeacherToSubject(@ModelAttribute TeacherSubject teacherSubject) {
        adminService.assignTeacherToSubject(teacherSubject.getSubject(), teacherSubject.getTeacher());
        return "redirect:/admin/subject/assignTeacherToSubject";
    }

}
