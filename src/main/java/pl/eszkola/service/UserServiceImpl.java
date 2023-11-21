package pl.eszkola.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.eszkola.model.User;
import pl.eszkola.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Getter
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordService;
    }

    @Override
    public void registerUser(User user) {
        if (checkIfUserExist(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        // kodowanie hasła
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // zapisywanie użytkownika
        userRepository.save(user);
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isPasswordValid(String password) {
        // Sprawdzenie, czy hasło zawiera co najmniej jedną dużą literę, jedną liczbę, co najmniej jeden znak specjalny i ma co najmniej 8 znaków
        return password.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}");
    }

    @Override
    public void updateUserInfo(Long userId, String address, String phone1, String phone2) {
        // Znajdź użytkownika na podstawie ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Aktualizuj dane użytkownika
        existingUser.setAddress(address);
        existingUser.setPhone1(phone1);
        existingUser.setPhone2(phone2);

        // Zapisz zaktualizowanego użytkownika w bazie danych
        userRepository.save(existingUser);
    }

    @Override
    public void addUser(User user) {
        // Sprawdzamy, czy użytkownik o danym email już istnieje
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }

        // Możemy również dodać walidację innych pól użytkownika przed zapisaniem

        // Zapisujemy użytkownika
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        // Sprawdzamy, czy użytkownik istnieje
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Usuwamy użytkownika
        userRepository.delete(userToDelete);
    }

    @Override
    public void updateUser(Long userId, User updatedUser) {
        // Sprawdzamy, czy użytkownik istnieje
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Aktualizujemy dane użytkownika
        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setPesel(updatedUser.getPesel());
        existingUser.setPhone1(updatedUser.getPhone1());
        existingUser.setPhone2(updatedUser.getPhone2());
        existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setRole(updatedUser.getRole());


        // Zapisujemy zaktualizowanego użytkownika
        userRepository.save(existingUser);
    }

}
