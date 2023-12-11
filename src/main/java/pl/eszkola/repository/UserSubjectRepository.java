package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.eszkola.model.UserSubject;

public interface UserSubjectRepository extends JpaRepository<UserSubject, Long> {
}
