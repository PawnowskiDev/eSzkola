package pl.eszkola.service;

import java.time.LocalDate;

public interface TeacherService {
    void giveGradeAndNote(Long studentId, Long subjectId, String note, double grade);

    boolean checkAttendance (Long userId, Long classId, LocalDate date);

    void excuseAttendance(String pesel);
}
