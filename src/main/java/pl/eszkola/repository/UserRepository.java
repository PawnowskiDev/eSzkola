package pl.eszkola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.UserType;

import java.util.List;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    boolean existsByEmail(String email);

    MyUser findByPesel(String pesel);

    @Query("SELECT u FROM MyUser u WHERE u.userType = :userType AND (LOWER(u.name) LIKE %:keyword% OR LOWER(u.surname) LIKE %:keyword%)")
    List<MyUser> findUsersByTypeAndKeyword(@Param("userType") String userType, @Param("keyword") String keyword);


    List<MyUser> findByUserType(UserType userType);
}








