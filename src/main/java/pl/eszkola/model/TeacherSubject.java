package pl.eszkola.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher_subject")
public class TeacherSubject {

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser teacher;

    public TeacherSubject(Subject subject, MyUser teacher) {
        this.subject = subject;
        this.teacher = teacher;
    }

    public TeacherSubject() {
    }

    public Subject getSubject() {
        return subject;
    }

    public MyUser getTeacher() {
        return teacher;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setTeacher(MyUser teacher) {
        this.teacher = teacher;
    }

    // Konstruktory, gettery, settery, itp.
}

