package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.eszkola.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    User findByPesel(String pesel);

    @Modifying
    @Query("update User u set u.name = ?1, u.surname = ?2, u.phone1 = ?3, u.phone2 = (CASE WHEN ?4 IS NOT NULL THEN ?4 ELSE u.phone2 END), u.adress = ?5  where u.email = ?6")
    void updateUserInfo(String name, String surname, String phone1, String phone2, String adress, String email);

    @Modifying
    @Query("update User u set u.password = ?1 where u.email = ?2")
    void updatePassword(String newPassword, String email);



}
