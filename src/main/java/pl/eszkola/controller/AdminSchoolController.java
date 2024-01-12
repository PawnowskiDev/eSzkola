package pl.eszkola.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.SchoolClass;
import pl.eszkola.model.Subject;
import pl.eszkola.repository.SchoolClassRepository;
import pl.eszkola.repository.SubjectRepository;
import pl.eszkola.service.AdminService;

@Controller
@RequestMapping("/admin/schoolClass")
public class AdminSchoolController {

    private final SchoolClassRepository schoolClassRepository;
    private final AdminService adminService;


    public AdminSchoolController(SchoolClassRepository schoolClassRepository, AdminService adminService) {
        this.schoolClassRepository = schoolClassRepository;
        this.adminService = adminService;

    }


    @GetMapping("/addSchoolClass")
    public String showAddSchoolClass (Model model) {
        model.addAttribute("schoolClass", new SchoolClass());
        return "admin/schoolClass/addSchoolClass";
    }

    @PostMapping("/addSchoolClass")
    public String addSchoolClass (@ModelAttribute SchoolClass schoolClass, Model model) {
        schoolClassRepository.save(schoolClass);
        model.addAttribute("successMessage", "Klasa szkolna dodana pomyślnie");
        return "redirect:/admin/schoolClass/addSchoolClass";
    }

    @GetMapping("/userToClass")
    public String showUserToClassForm(Model model) {
        model.addAttribute("userClass", adminService);
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("schoolClasses", adminService.getAllClass());
        return "admin/schoolClass/userToClass";
    }

    @PostMapping("/userToClass")
    public String assignUserToClass(@RequestParam Long userId, @RequestParam Long schoolClass) {
        adminService.assignUserToClass(userId, schoolClass);
        return "redirect:/admin/schoolClass/userToClass";
    }



}
