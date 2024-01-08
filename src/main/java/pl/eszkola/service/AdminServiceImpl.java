package pl.eszkola.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import pl.eszkola.model.*;
import pl.eszkola.repository.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final SchoolClassRepository schoolClassRepository;

    private final SubjectRepository subjectRepository;

    private final UserSubjectRepository userSubjectRepository;

    private final UserSchoolClassRepository userSchoolClassRepository;


    public AdminServiceImpl(UserRepository userRepository, SchoolClassRepository schoolClassRepository, SubjectRepository subjectRepository,
                            UserSubjectRepository userSubjectRepository, UserSchoolClassRepository userSchoolClassRepository) {
        this.userRepository = userRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.subjectRepository = subjectRepository;
        this.userSubjectRepository = userSubjectRepository;
        this.userSchoolClassRepository = userSchoolClassRepository;
    }

    @Override
    public void addUser(MyUser myUser) {

        if (userRepository.existsByEmail(myUser.getPesel())) {
            throw new RuntimeException("User with this PESEL already exists");
        }
        validateUserFields(myUser);
        userRepository.save(myUser);
    }

    @Override
    public void deleteUser(Long userId) {

        MyUser userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        userRepository.delete(userToDelete);
    }

    @Override
    public void updateUser(Long userId, MyUser updatedUser) {

        MyUser existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAdress(updatedUser.getAdress());
        existingUser.setPesel(updatedUser.getPesel());
        existingUser.setPhone1(updatedUser.getPhone1());
        existingUser.setPhone2(updatedUser.getPhone2());
        existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setUserType(updatedUser.getUserType());


        userRepository.save(existingUser);
    }

    private void validateUserFields(MyUser myUser) {

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
        // ustawianie hasła jezeli jest puste
        if (StringUtils.isEmpty(myUser.getPassword())) {
            myUser.setPassword(generateRandomPassword());
        }
        validateEmailFormat(myUser.getEmail());
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

    @Override
    public void addSchoolClass(SchoolClass schoolClass) {
        // waliduje parametry dla schoolClass
        validateSchoolClassFields(schoolClass);

        // zapisuje
        schoolClassRepository.save(schoolClass);

    }

    private void validateSchoolClassFields(SchoolClass schoolClass) {

        if (schoolClass.getClassName() == null || schoolClass.getClassName().isEmpty()) {
            throw new IllegalArgumentException("School Name cannot be empty");
        }
        if (schoolClass.getClassProfile() == null || schoolClass.getClassProfile().isEmpty()) {
            throw new IllegalArgumentException("School Class Profile cannot be empty");
        }
        if (schoolClass.getIsEvening() == null) {
            throw new IllegalArgumentException("Type of school attendance cannot be empty");
        }
    }

    @Override
    public List<MyUser> getUsersByTypeAndKeyword(String userType, String keyword) {

        List<UserType> userTypes = List.of(UserType.TEACHER, UserType.STUDENT);

        if (keyword == null || keyword.isEmpty()) {
            return userRepository.findUsersByTypeAndKeyword(userTypes, keyword); // Jeśli keyword jest pusty, użyj metody bez keyworda
        } else {
            List<MyUser> users = userRepository.findUsersByTypeAndKeyword(userTypes, keyword);
            System.out.println("Wyszukani użytkownicy: " + users);
            return users;
        }
    }
    @Override
    public Subject createSubject(String subjectName, String subjectDescription) {
        Subject newSubject = new Subject();
        newSubject.setSubjectName(subjectName);
        newSubject.setSubjectDescription(subjectDescription);
        return subjectRepository.save(newSubject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
    @Override
    public List<MyUser> getAllTeachers() {
        return userRepository.findByUserType(UserType.TEACHER);
    }

    @Override
    public Object getAllUsers() {
        return userRepository.findAll();
    }


    public void assignUserToSubject(Long userId, Long subjectId) {
        MyUser user = getUserById(userId);
        Subject subject = getSubjectById(subjectId);

        if (user != null && subject != null) {
            UserSubject userSubject = new UserSubject(user, subject);
            userSubjectRepository.save(userSubject);
        } else {
            throw new EntityNotFoundException("Użytkownik lub przedmiot nie istnieje");
        }
    }

    @Override
    public Object getAllClass() {
        return null;
    }

    @Override
    public void assignUserToClass(Long userId, Long classId) {
        MyUser user = getUserById(userId);
        SchoolClass schoolClass = getSchoolClassById(classId);

        if(user != null && schoolClass != null) {
            UserSchoolClass userSchoolClass = new UserSchoolClass(user, schoolClass);
            userSchoolClassRepository.save(userSchoolClass);
        } else {
            throw new EntityNotFoundException("Użytkownik lub klasa nie istnieje");
        }

    }

    private Subject getSubjectById(Long subjectId) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        return optionalSubject.orElse(null);
    }

    private MyUser getUserById(Long userId) {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    private SchoolClass getSchoolClassById(Long classId) {
        Optional<SchoolClass> optionalSchoolClass = schoolClassRepository.findById(classId);
        return optionalSchoolClass.orElse(null);
    }
}

