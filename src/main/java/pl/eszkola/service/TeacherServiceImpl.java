package pl.eszkola.service;

import org.springframework.stereotype.Service;
import pl.eszkola.model.*;
import pl.eszkola.repository.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final GradesRepository gradesRepository;
    private final AttendanceRepository attendanceRepository;

    private final SchoolClassRepository schoolClassRepository;


    public TeacherServiceImpl(UserRepository userRepository, SubjectRepository subjectRepository, GradesRepository gradesRepository, AttendanceRepository attendanceRepository, SchoolClassRepository schoolClassRepository) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.gradesRepository = gradesRepository;
        this.attendanceRepository = attendanceRepository;
        this.schoolClassRepository = schoolClassRepository;
    }
    @Override
    public MyUser getTeacherByName(String name) {
        return userRepository.findByName(name);
    }


    @Override
    public List<SchoolClass> getTeacherClasses(Long userId) {
        Optional<MyUser> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()) {
            MyUser teacher = userOptional.get();
            return schoolClassRepository.findByTeacher(teacher);
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public List<MyUser> getStudentsInClass(SchoolClass schoolClass) {
        return userRepository.findBySchoolClass(schoolClass);
    }

    @Override
    public SchoolClass getClassDetails(Long schoolClassId) {
        Optional<SchoolClass> optionalSchoolClass = schoolClassRepository.findById(schoolClassId);
        return optionalSchoolClass.orElse(null);
    }

    @Override
    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }

}
