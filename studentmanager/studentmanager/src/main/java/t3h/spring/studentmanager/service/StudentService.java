package t3h.spring.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.spring.studentmanager.dao.StudentDao;
import t3h.spring.studentmanager.models.Student;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    public List<Student> getAllStudents(){
        return studentDao.getAllStudents();
    }

    public Student save(Student student){
        return studentDao.save(student);
    }
}
