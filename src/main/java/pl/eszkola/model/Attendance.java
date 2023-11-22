package pl.eszkola.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class aClass;
    private LocalDate date;
    private AttendanceStatus isPresent;

    public Attendance() {
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public User getUser() {
        return user;
    }

    public Class getaClass() {
        return aClass;
    }

    public LocalDate getDate() {
        return date;
    }

    public AttendanceStatus getIsPresent() {
        return isPresent;
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

    public enum AttendanceStatus {
        YES,
        NO
    }
}
