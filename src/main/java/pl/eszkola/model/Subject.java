package pl.eszkola.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subject_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private String subject_name;
    private String subject_description;

    public Subject() {
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public String getSubject_description() {
        return subject_description;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public void setSubject_description(String subject_description) {
        this.subject_description = subject_description;
    }
}
