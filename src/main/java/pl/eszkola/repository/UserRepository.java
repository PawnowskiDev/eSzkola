package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.eszkola.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByNameAndSurname(String name, String surname);

    boolean existsByEmail(String email);
}
