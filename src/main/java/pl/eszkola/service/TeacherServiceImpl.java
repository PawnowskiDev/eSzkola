package pl.eszkola.service;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.eszkola.model.*;
import pl.eszkola.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final GradesRepository gradesRepository;
    private final AttendanceRepository attendanceRepository;

    private final SchoolClassRepository schoolClassRepository;


    public TeacherServiceImpl(UserRepository userRepository, SubjectRepository subjectRepository, GradesRepository gradesRepository, AttendanceRepository attendanceRepository, SchoolClassRepository schoolClassRepository) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.gradesRepository = gradesRepository;
        this.attendanceRepository = attendanceRepository;
        this.schoolClassRepository = schoolClassRepository;
    }


    @Override
    public void giveGradeAndNote(Long studentId, Long subjectId, String note, double grade) {
        // sprawdzamy czy student istnieje
        userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        // sprawdzamy czy przedmiot istnieje
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + subjectId));
        // sprawdzamy czy ocena mieści się w zakresie
        if (grade < 1 || grade > 6) {
            throw new IllegalArgumentException("Grade must be between 1 and 6");
        }
        // sprawdzamy czy notatka nie jest pusta
        if (StringUtils.isEmpty(note)) {
            throw new IllegalArgumentException("Note cannot be empty");
        }

        // tworzymy nową ocenę
        Grade newGrade = new Grade();
        newGrade.setSubject(subject);
        newGrade.setGrade(grade);
        newGrade.setNote(note);

        // Zapisujemy ocenę
        gradesRepository.save(newGrade);
    }

    @Override
    public boolean checkAttendance(String pesel, Long classId, LocalDate date) {
        // sprawdzamy czy istnieje rekord dla obecnosci uzytkownika danej klasy w danym dniu
        Attendance attendanceRecord = (Attendance) attendanceRepository.findAttendanceByPeselAndDate(pesel, date);

        // zwracamy true, jezeli uzytkownik był obecny
        return attendanceRecord != null && attendanceRecord.isPresent();
    }

//    @Override
//    public void excuseAttendance(String pesel) {
//        // Znajdź użytkownika na podstawie PESEL
//        MyUser myUser = userRepository.findByPesel(pesel);
//
//        if (myUser != null) {
//            // Znajdź rekord obecności dla danego użytkownika i dzisiejszej daty
//            Attendance attendanceRecord = (Attendance) attendanceRepository.findAttendanceByPeselAndDate(myUser.getPesel(), LocalDate.now());
//
//            if (attendanceRecord != null) {
//                // Ustaw isExcused na true
//                attendanceRecord.setExcused(Attendance.ExcuseStatus.EXCUSED);
//
//                // Zapisz zaktualizowany rekord obecności
//                attendanceRepository.save(attendanceRecord);
//            } else {
//                // Utwórz nowy rekord obecności i ustaw isExcused
//                Attendance newAttendanceRecord = new Attendance();
//                newAttendanceRecord.setMyUser(myUser);  // Ustaw użytkownika
//                newAttendanceRecord.setDate(LocalDate.now());  // Ustaw dzisiejszą datę
//                newAttendanceRecord.setExcused(Attendance.ExcuseStatus.EXCUSED);
//
//                // Zapisz nowy rekord obecności
//                attendanceRepository.save(newAttendanceRecord);
//            }
//        } else {
//            throw new IllegalArgumentException("Student not found with PESEL: " + pesel);
//        }
//    }

    @Override
    public void excuseAttendance(String pesel) {
        MyUser myUser = userRepository.findByPesel(pesel);

        if (myUser != null) {
            // Znajdź rekord obecności dla danego użytkownika i dzisiejszej daty
            List<Attendance> attendances = attendanceRepository.findAttendanceByPeselAndDate(pesel, LocalDate.now());
            if (!attendances.isEmpty()) {
                Attendance attendanceRecord = attendances.get(0);
                attendanceRecord.setExcused(Attendance.ExcuseStatus.EXCUSED);
            } else {
                // Utwórz nowy rekord obecności i ustaw isExcused
                Attendance newAttendanceRecord = new Attendance();
                newAttendanceRecord.setMyUser(myUser);
                newAttendanceRecord.setDate(LocalDate.now());
                newAttendanceRecord.setExcused(Attendance.ExcuseStatus.EXCUSED);
                // Dodaj nowy rekord obecności do listy obecności użytkownika
                myUser.getAttendances().add(newAttendanceRecord);
            }
            // Zapisz użytkownika, co spowoduje również zapis nowego rekordu obecności
            userRepository.save(myUser);
        } else {
            throw new IllegalArgumentException("Student not found with PESEL: " + pesel);
        }
    }

    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }

    public SchoolClass getClassById(Long classId) {
        return schoolClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));
    }


}
