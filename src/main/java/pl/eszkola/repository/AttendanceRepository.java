package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.eszkola.model.Attendance;

import java.time.LocalDate;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Attendance findByUserIdAndClassIdAndDate (Long userId, Long classId, LocalDate date);
}
