package pl.eszkola.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_school_class")
public class UserSchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private MyUser user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;


    public UserSchoolClass() {
    }

    public UserSchoolClass(MyUser user, SchoolClass schoolClass) {
        this.user = user;
        this.schoolClass = schoolClass;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }
}
