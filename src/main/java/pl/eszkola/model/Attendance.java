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
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser myUser;

    @Getter
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "schoolClass_id", nullable = false)
    private SchoolClass schoolClass;

    @Getter
    @Column(name = "is_excused")
    @Enumerated(EnumType.STRING)
    private ExcuseStatus isExcused;

    @Getter
    private LocalDate date;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "is_present")
    private AttendanceStatus isPresent;

    public Attendance() {
    }

    // Gettery i settery

    public boolean isPresent() {
        return AttendanceStatus.YES.equals(isPresent);
    }

    public void setExcused(ExcuseStatus isExcused) {
        this.isExcused = isExcused;
    }

    public void setMyUser(MyUser myUser) {

    }

    public void setDate(LocalDate now) {
    }

    public enum ExcuseStatus {
        YES,
        NO,
        EXCUSED
    }

    public enum AttendanceStatus {
        YES,
        NO
    }
}
