package pl.eszkola.model;

import jakarta.persistence.*;

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

    }
}
