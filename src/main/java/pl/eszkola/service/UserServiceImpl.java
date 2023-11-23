package pl.eszkola.service;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.eszkola.model.Attendance;
import pl.eszkola.model.Grades;
import pl.eszkola.model.User;
import pl.eszkola.repository.AttendanceRepository;
import pl.eszkola.repository.GradesRepository;
import pl.eszkola.repository.SubjectRepository;
import pl.eszkola.repository.UserRepository;

import javax.security.auth.Subject;
import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubjectRepository subjectRepository;
    private final GradesRepository gradesRepository;
    private final AttendanceRepository attendanceRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordService,
                           SubjectRepository subjectRepository, GradesRepository gradesRepository,
                           AttendanceRepository attendanceRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordService;
        this.subjectRepository = subjectRepository;
        this.gradesRepository = gradesRepository;
        this.attendanceRepository = attendanceRepository;
    }


    @Override
    public boolean isPasswordValid(String password) {
        // sprawdzamy czy hasło zawiera co najmniej jedną dużą literę, jedną liczbę, co najmniej jeden znak specjalny i ma co najmniej 8 znaków
        return password.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}");
    }

    @Override
    public void updateUserInfo(Long userId, String address, String phone1, String phone2) {
        // znajdź użytkownika na podstawie ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // aktualizuj dane użytkownika
        existingUser.setAddress(address);
        existingUser.setPhone1(phone1);
        existingUser.setPhone2(phone2);

        // zapisz zaktualizowanego użytkownika w bazie danych
        userRepository.save(existingUser);
    }

    @Override
    public void giveGradeAndNote(Long studentId, Long subjectId, String note, double grade) {
        // sprawdzamy czy student istnieje
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        // sprawdzamy czy przedmiot istnieje
        Subject subject = subjectRepository.findById(String.valueOf(subjectId))
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
        Grades newGrade = new Grades();
        newGrade.setUser(student);
        newGrade.setSubject(subject);
        newGrade.setGrade(grade);
        newGrade.setNote(note);

        // Zapisujemy ocenę
        gradesRepository.save(newGrade);
    }

    @Override
    public boolean checkAttendance (Long userId, Long classId, LocalDate date) {
        // sprawdzamy czy istnieje rekord dla obecnosci uzytkownika danej klasy w danym dniu
        Attendance attendanceRecord = attendanceRepository.findByUserIdAndClassIdAndDate(userId, classId, date);

        // zwracamy true, jezeli uzytkownik był obecny
        return attendanceRecord != null && attendanceRecord.isPresent();
    }


    @Override
    public User getUserByPESEL(String pesel) {
        return userRepository.findByPesel(pesel);
    }


    @Override
    public void excuseAttendance(String pesel) {
        // Znajdź użytkownika na podstawie PESEL
        User user = userRepository.findByPesel(pesel);

        if (user != null) {
            // Znajdź rekord obecności dla danego użytkownika (przyjmuję, że istnieje tylko jeden rekord na dzień)
            Attendance attendanceRecord = attendanceRepository.findByUserIdAndDate(user.getUser_id(), LocalDate.now());

            if (attendanceRecord != null) {
                // Ustaw isExcused na true
                attendanceRecord.setExcused(true);

                // Zapisz zaktualizowany rekord obecności
                attendanceRepository.save(attendanceRecord);
            } else {
                // Utwórz nowy rekord obecności i ustaw isExcused
                Attendance newAttendanceRecord = new Attendance();
                newAttendanceRecord.setUser(user);
                newAttendanceRecord.setDate(LocalDate.now());
                newAttendanceRecord.setExcused(newAttendanceRecord.isPresent());

                // Zapisz nowy rekord obecności
                attendanceRepository.save(newAttendanceRecord);            }
        } else {
            throw new IllegalArgumentException("Student not found with PESEL: " + pesel);
        }
    }
}
