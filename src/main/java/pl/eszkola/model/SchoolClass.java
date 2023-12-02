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

    public SchoolClass() {
    }

    public Long getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getClassProfile() {
        return classProfile;
    }

    public EveningType getIsEvening() {
        return isEvening;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassProfile(String classProfile) {
        this.classProfile = classProfile;
    }

    public void setIsEvening(EveningType isEvening) {
        this.isEvening = isEvening;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @OneToMany(mappedBy = "schoolClass")
    private List<Grade> grades;
}
