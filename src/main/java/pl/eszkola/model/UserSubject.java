package pl.eszkola.model;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.CascadeType.*;

@Getter
@Entity
@Table(name = "user_subject")
public class UserSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSubjectId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser myUser;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public UserSubject(MyUser user, Subject subject) {
        this.myUser = user;
        this.subject = subject;
    }

    public UserSubject() {
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


