package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.eszkola.model.Grade;

public interface GradesRepository extends JpaRepository<Grade, String> {

}

