package pl.eszkola.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "class")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @Column(name = "class_name")
    private String className;
    @Column(name = "class_profile")
    private String classProfile;
    @Column(name = "is_evening")
    @Enumerated(EnumType.STRING)
    private EveningType isEvening;

    public enum EveningType {
        YES,
        NO
    }

    @OneToMany(mappedBy = "schoolClass")
    private List<Grade> grades;
}
