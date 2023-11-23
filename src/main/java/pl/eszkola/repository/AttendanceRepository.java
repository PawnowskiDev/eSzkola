package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import pl.eszkola.model.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Attendance findByUserIdAndClassIdAndDate (Long userId, Long classId, LocalDate date);

    List<Attendance> findByUserAndIsPresent(User user, Attendance.AttendanceStatus isPresent);

    Attendance findByUserIdAndDate(Long userId, LocalDate now);
}
