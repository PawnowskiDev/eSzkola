package pl.eszkola.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.SchoolClass;
import pl.eszkola.repository.SchoolClassRepository;
import pl.eszkola.service.TeacherService;
import pl.eszkola.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final SchoolClassRepository schoolClassRepository;
    private final UserService userService;
    @Autowired
    public TeacherController(TeacherService teacherService, SchoolClassRepository schoolClassRepository, UserService userService) {
        this.teacherService = teacherService;
        this.schoolClassRepository = schoolClassRepository;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String teacherDashboard(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            MyUser teacher = userService.getUserById(userId);
            List<SchoolClass> teacherClasses = teacherService.getTeacherClasses(userId);
            model.addAttribute("teacherClasses", teacherClasses);
            model.addAttribute("userId", userId);

            return "teacher/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/classDetailsForm")
    public String classDetailsForm(Model model) {
        List<SchoolClass> allClasses = teacherService.getAllClasses();
        model.addAttribute("allClasses", allClasses);
        return "teacher/classDetailsForm";
    }

    @PostMapping("/classDetails")
    public String viewClassDetails(@RequestParam Long schoolClassId, Model model) {
        SchoolClass schoolClass = teacherService.getClassDetails(schoolClassId);
        model.addAttribute("schoolClass", schoolClass);
        return "teacher/classDetails";
    }
}


