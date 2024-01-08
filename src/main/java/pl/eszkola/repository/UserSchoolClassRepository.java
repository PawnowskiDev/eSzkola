package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.eszkola.model.UserSchoolClass;

public interface UserSchoolClassRepository extends JpaRepository<UserSchoolClass, Long> {
}
