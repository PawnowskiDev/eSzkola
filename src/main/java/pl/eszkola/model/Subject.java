package pl.eszkola.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "subject_name", length = 50)
    private String subjectName;

    @Column(name = "subject_description", length = 255)
    private String subjectDescription;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private MyUser myUser;

    @OneToMany(mappedBy = "subject")
    private List<UserSubject> userSubjects;

    public Subject() {
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    @OneToMany(mappedBy = "subject")
    private List<Grade> grades;

    public Integer getId() {
        return subjectId;
    }
}



