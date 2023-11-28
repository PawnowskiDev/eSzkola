package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;// TODO User od spring
import pl.eszkola.model.Attendance;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.SchoolClass;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Modifying
    @Query("update Attendance a set a.isPresent = :status where a.myUser.user_id = :userId and a.schoolClass = :schoolClass and a.date = :date")
    void updateAttendanceStatus(@Param("status") Attendance.AttendanceStatus status,
                                @Param("userId") Long user_id,
                                @Param("schoolClass") SchoolClass schoolClass,
                                @Param("date") LocalDate date);

    @Query("SELECT a FROM Attendance a WHERE a.myUser.user_id = :userId AND a.date = :date")
    List<Attendance> findAttendanceByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);
}

