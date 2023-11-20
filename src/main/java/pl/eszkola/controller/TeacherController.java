package pl.eszkola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.eszkola.model.User;
import pl.eszkola.repository.UserRepository;
import pl.eszkola.service.UserService;

public class TeacherController {

    private final UserService userService;

    @Autowired
    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/giveGrade")
    public ResponseEntity<String> giveRating(@RequestParam Long userId, @RequestParam String grade) {
        userService.giveRating(userId, grade);
        return ResponseEntity.ok("Rating issued");
    }

    @PostMapping("/giveNote")
    public ResponseEntity<String> giveNote(@RequestParam Long userId, @RequestParam String note) {
        userService.giveNote(userId, note);
        return ResponseEntity.ok("Note issued");
    }

    // dodac logike isPresent do sprawdzania obecności
}
