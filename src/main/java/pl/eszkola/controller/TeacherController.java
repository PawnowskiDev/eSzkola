package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.eszkola.service.TeacherService;
import pl.eszkola.service.TeacherServiceImpl;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        // logika
        return "/templates/teacher/dashboard.html";
    }

    @GetMapping("/giveGradeAndNote")
    public String showGiveGradeAndNoteForm(Model model) {
        // Logika związana z wystawianiem oceny i noty
        return "/templates/teacher/giveGradeAndNoteForm.html";
    }

    @PostMapping("/giveGradeAndNote")
    public String giveGradeAndNote(@RequestParam Long studentId, @RequestParam Long subjectId, @RequestParam String note, @RequestParam double grade) {
        teacherService.giveGradeAndNote(studentId, subjectId,note,grade);
        return "redirect:/teacher/dashboard";
    }

    @GetMapping("/checkAttendance")
    public String showCheckAttendanceForm(Model model) {
        // Logika związana ze sprawdzaniem obecności
        return "/templates/teacher/checkAttendanceForm.html";
    }

    @PostMapping("/checkAttendance")
    public String checkAttendance(@RequestParam String pesel, @RequestParam Long classId, @RequestParam String date) {
        // Przetwarzanie sprawdzania obecności
        // ...
        return "redirect:/teacher/dashboard";
    }

    @GetMapping("/excuseAttendance")
    public String showExcuseAttendanceForm(Model model) {
        // Logika związana z usprawiedliwianiem nieobecności
        return "/templates/teacher/excuseAttendanceForm.html";
    }

    @PostMapping("/excuseAttendance")
    public String excuseAttendance(@RequestParam String pesel) {
        teacherService.excuseAttendance(pesel);
        return "redirect:/teacher/dashboard";
    }
}
