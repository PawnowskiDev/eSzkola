package pl.eszkola.service;

import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.SchoolClass;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {


    MyUser getTeacherByName(String name);


    List<SchoolClass> getTeacherClasses(MyUser teacher);

    List<MyUser> getStudentsInClass(SchoolClass schoolClass);

    SchoolClass getClassDetails(Long schoolClassId);

    List<SchoolClass> getAllClasses();
}
