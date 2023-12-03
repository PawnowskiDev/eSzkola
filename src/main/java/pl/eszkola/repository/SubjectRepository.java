package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.eszkola.model.Subject;


public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
