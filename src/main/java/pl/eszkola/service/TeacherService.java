package pl.eszkola.service;

import org.springframework.stereotype.Service;
import pl.eszkola.model.MyUser;
import pl.eszkola.model.SchoolClass;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {


    MyUser getTeacherByUsername(String username);

    List<SchoolClass> getTeacherClasses(MyUser teacher);

    List<MyUser> getStudentsInClass(SchoolClass schoolClass);
}
