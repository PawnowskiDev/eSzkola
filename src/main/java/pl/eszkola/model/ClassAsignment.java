package pl.eszkola.model;

import jakarta.persistence.*;
import org.hibernate.usertype.UserType;

@Entity
@Table(name = "class_assignment")
public class ClassAsignment {

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;


    public enum UserType {
        TEACHER,
        STUDENT
    }
}
