package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.eszkola.model.MyUser;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByEmail(String email);

    Optional<MyUser> findById(Long id);

    boolean existsByEmail(String email);

    MyUser findByPesel(String pesel);



    @Query("SELECT u FROM MyUser u WHERE u.userType = 'teacher' AND (LOWER(u.name) LIKE %:keyword% OR LOWER(u.surname) LIKE %:keyword%)")
    List<MyUser> findTeachersByKeyword(@Param("keyword") String keyword);

    @Query("SELECT u FROM MyUser u WHERE u.userType = 'student' AND (LOWER(u.name) LIKE %:keyword% OR LOWER(u.surname) LIKE %:keyword%)")
    List<MyUser> findStudentsByKeyword(@Param("keyword") String keyword);

    }







