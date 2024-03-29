package pl.eszkola.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.Subject;
import pl.eszkola.model.UserType;
import pl.eszkola.repository.UserRepository;

import java.util.*;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Map<Long, MyUser> userMap = new HashMap<>();


    @Override
    public List<MyUser> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public MyUser getUserById(Long userId) {
        return userMap.get(userId);
    }

    @Override
    public void saveUser(MyUser myUser) {
        userRepository.save(myUser);
    }

    @Override
    public MyUser updateUser(Long userId, MyUser myUser) {
        if (userMap.containsKey(userId)) {
            myUser.setUser_id(userId);
            userMap.put(userId, myUser);
        }
        return myUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId); // tutaj masz repository ze spring data, samo ci tworzy te methodydobra j
    }

    // a tu już

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository  = userRepository;
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}");

    }

    @Override
    public void validateEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    @Override
    public void updateUserInfo(Long userId, String adress, String phone1, String phone2) {
        // znajdź użytkownika na podstawie ID
        MyUser existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // aktualizuj dane użytkownika
        existingUser.setAdress(adress);
        existingUser.setPhone1(phone1);
        existingUser.setPhone2(phone2);

        // zapisz zaktualizowanego użytkownika w bazie danych
        userRepository.save(existingUser);
    }


    @Override
    public MyUser getUserByPESEL(String pesel) {
        return userRepository.findByPesel(pesel);
    }

    @Override
    public void validateUserFields(MyUser myUser) {

        if (myUser.getName() == null || myUser.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (myUser.getSurname() == null || myUser.getSurname().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be empty");
        }
        if (myUser.getAdress() == null || myUser.getAdress().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (myUser.getPesel() == null || myUser.getPesel().isEmpty()) {
            throw new IllegalArgumentException("PESEL cannot be empty");
        }
        if (myUser.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth cannot be empty");
        }
        if (myUser.getGender() == null || myUser.getGender().isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be empty");
        }
        if (myUser.getUserType() == null) {
            throw new IllegalArgumentException("User Type cannot be empty");
        }

        validateEmailFormat(myUser.getEmail());
    }

    @Override
    public List<MyUser> getUsersByTypeAndKeyword(String userType, String keyword) {

        List<UserType> userTypes = List.of(UserType.TEACHER, UserType.STUDENT);

        if (keyword == null || keyword.isEmpty()) {
            return userRepository.findUsersByTypeAndKeyword(userTypes, ""); // Jeśli keyword jest pusty, użyj metody bez keyworda
        } else {
            List<MyUser> users = userRepository.findUsersByTypeAndKeyword(userTypes, keyword);
            System.out.println("Wyszukani użytkownicy: " + users);
            return users;
        }
    }

    @Override
    public MyUser findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public MyUser findByUsername(String name) {
        return null;
    }

    @Override
    public List<Subject> findByUserType(MyUser user) {
        return null;
    }
}

