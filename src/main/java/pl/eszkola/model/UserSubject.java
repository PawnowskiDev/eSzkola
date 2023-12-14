package pl.eszkola.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_subject")
public class UserSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSubjectId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser myUser;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public UserSubject(MyUser user, Subject subject) {
        this.myUser = user;
        this.subject = subject;
    }

    public void setUserSubjectId(Long userSubjectId) {
        this.userSubjectId = userSubjectId;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}


