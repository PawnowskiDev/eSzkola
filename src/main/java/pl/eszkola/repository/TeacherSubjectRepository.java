package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.eszkola.model.TeacherSubject;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
}
