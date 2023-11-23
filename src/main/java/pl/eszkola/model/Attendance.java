package pl.eszkola.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class aClass;

    @Column(name = "is_excused")
    private boolean isExcused;

    @Getter
    private LocalDate date;
    @Getter
    private AttendanceStatus isPresent;

    public Attendance() {
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setIsPresent(AttendanceStatus isPresent) {
        this.isPresent = isPresent;
    }
    public boolean isPresent() {
        return AttendanceStatus.YES.equals(isPresent);
    }

    public void setExcused(boolean isExcused) {
        this.isExcused = isExcused;
    }

    public enum AttendanceStatus {
        YES,
        NO
    }
}
