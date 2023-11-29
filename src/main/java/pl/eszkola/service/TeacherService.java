package pl.eszkola.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface TeacherService {

    void giveGradeAndNote(Long studentId, Long subjectId, String note, double grade);

    boolean checkAttendance(String pesel, Long classId, LocalDate date);

    void excuseAttendance(String pesel);
}
