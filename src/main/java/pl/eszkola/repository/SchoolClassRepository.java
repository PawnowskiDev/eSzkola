package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.eszkola.model.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

}
