package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.eszkola.model.MyUser;
import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByEmail(String email);

    Optional<MyUser> findById(Long id);

    boolean existsByEmail(String email);

    MyUser findByPesel(String pesel);


}
