package pl.eszkola.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser myUser;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "schoolClass_id", nullable = false)
    private SchoolClass schoolClass;

    private double grade;

    private String note;

    // konstruktor, gettery, settery...

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(MyUser user) {
        this.myUser = user;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
