package pl.eszkola.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;
import pl.eszkola.model.User;
import pl.eszkola.repository.UserRepository;

import java.security.SecureRandom;
import java.util.Random;

public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        // Sprawdzamy, czy użytkownik o danym email już istnieje
        if (userRepository.existsByEmail(user.getPesel())) {
            throw new RuntimeException("User with this PESEL already exists");
        }
        // walidacja innych pól użytkownika przed zapisaniem
        validateUserFields(user);

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

    private void validateUserFields(User user) {

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (user.getSurname() == null || user.getSurname().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be empty");
        }
        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (user.getPesel() == null || user.getPesel().isEmpty()) {
            throw new IllegalArgumentException("PESEL cannot be empty");
        }
        if (user.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth cannot be empty");
        }
        if (user.getGender() == null || user.getGender().isEmpty()) {
            throw new IllegalArgumentException("Gender cannot be empty");
        }
        if (user.getUserType() == null || user.getUserType().isEmpty()) {
            throw new IllegalArgumentException("User Type cannot be empty");
        }
        // ustawianie hasła jezeli jest puste
        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(generateRandomPassword());
        }
        validateEmailFormat(user.getEmail());
    }

    private String generateRandomPassword() {
        // Dostępne znaki do wygenerowania hasła
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Długość generowanego hasła
        int length = 12;
        // Inicjalizacja generatora liczb losowych
        Random random = new SecureRandom();
        // tworzenie ciągu znaków o podanej długości length
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
    @Override
    public void validateEmailFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
