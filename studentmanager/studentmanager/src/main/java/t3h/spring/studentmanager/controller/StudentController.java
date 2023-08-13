package t3h.spring.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import t3h.spring.studentmanager.models.Student;
import t3h.spring.studentmanager.service.StudentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// http://0.0.0.0:8080/api/hello
@RestController
@RequestMapping("api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    @ResponseBody
    public Student save(Student student){
        return studentService.save(student);
    }

}
