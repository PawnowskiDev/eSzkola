package pl.eszkola.service;

import org.springframework.stereotype.Service;
import pl.eszkola.model.*;
import pl.eszkola.repository.*;

import java.util.List;

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
    public MyUser getTeacherByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<SchoolClass> getTeacherClasses(MyUser teacher) {
        return schoolClassRepository.findByTeacher(teacher);
    }

    @Override
    public List<MyUser> getStudentsInClass(SchoolClass schoolClass) {
        return userRepository.findBySchoolClass(schoolClass);
    }

}
