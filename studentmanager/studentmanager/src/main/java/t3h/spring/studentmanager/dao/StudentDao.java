package t3h.spring.studentmanager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import t3h.spring.studentmanager.entity.StudentEntity;
import t3h.spring.studentmanager.models.Student;
import t3h.spring.studentmanager.repository.StudentRepository;

import java.util.List;

@Component
public class StudentDao {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.getAllStudent().stream().map(student -> {
            return student.toData();
        }).toList();
    }

    public Student save(Student student){
        StudentEntity newStudent = new StudentEntity(student);
        return studentRepository.save(newStudent).toData();
    }
}
