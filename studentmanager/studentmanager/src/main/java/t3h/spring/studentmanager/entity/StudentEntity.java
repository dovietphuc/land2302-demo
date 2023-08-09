package t3h.spring.studentmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import t3h.spring.studentmanager.models.Student;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private long dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    public StudentEntity(){
        this.id = UUID.randomUUID();
    }

    public StudentEntity(Student student){
        this.id = student.getId();
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
        this.name = student.getName();
        this.gender = student.getGender();
        this.dateOfBirth = student.getDateOfBirth();
        this.address = student.getAddress();
    }

    public Student toData(){
        Student student = new Student();
        student.setId(this.id);
        student.setName(this.name);
        student.setGender(this.gender);
        student.setAddress(this.address);
        student.setDateOfBirth(this.dateOfBirth);
        return student;
    }
}
