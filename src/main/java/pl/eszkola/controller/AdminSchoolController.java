package pl.eszkola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.SchoolClass;
import pl.eszkola.repository.SchoolClassRepository;
import pl.eszkola.service.AdminService;
import pl.eszkola.service.AdminServiceImpl;

@Controller
@RequestMapping("/schoolClass")
public class AdminSchoolController {

    private final SchoolClassRepository schoolClassRepository;


    public AdminSchoolController(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @GetMapping("/schoolClassDashboard")
    public String showDashboard() {
        // logika
        return "admin/schoolClass/schoolClassDashboard";
    }

    @GetMapping("/addSchoolClass")
    public String showAddSchoolClass (SchoolClass schoolClass) {
        // Logika związana z wystawianiem oceny i noty
        return "admin/schoolClass/addSchoolClass";
    }

    @PostMapping("/addSchoolClass")
    public String addSchoolClass (@ModelAttribute SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return "redirect:/admin/schoolClass/addSchoolClass";
    }
}
