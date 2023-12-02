package pl.eszkola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.eszkola.model.SchoolClass;
import pl.eszkola.repository.SchoolClassRepository;
import pl.eszkola.service.AdminService;
import pl.eszkola.service.AdminServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminSchoolController {

    private final SchoolClassRepository schoolClassRepository;


    public AdminSchoolController(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }


    @GetMapping("/schoolClass/addSchoolClass")
    public String showAddSchoolClass (Model model) {
        model.addAttribute("schoolClass", new SchoolClass());
        return "admin/schoolClass/addSchoolClass";
    }

    @PostMapping("/schoolClass/addSchoolClass")
    public String addSchoolClass (@ModelAttribute SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return "redirect:admin/schoolClass/addSchoolClass";
    }
}
