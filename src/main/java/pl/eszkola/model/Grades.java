package pl.eszkola.model;

import jakarta.persistence.*;

import javax.security.auth.Subject;

@Entity
@Table(name = "grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String note;
    private double grade;

    public Grades() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getNote() {
        return note;
    }

    public double getGrade() {
        return grade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setSubject(Subject subject) {
    }
}
