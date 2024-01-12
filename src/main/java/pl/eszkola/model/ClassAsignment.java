package pl.eszkola.model;

import jakarta.persistence.*;
import org.hibernate.usertype.UserType;

@Entity
@Table(name = "class_assignment")
public class ClassAsignment {
    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;
    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private MyUser  myUser;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;


    public enum UserType {
        TEACHER,
        STUDENT
    }
}
