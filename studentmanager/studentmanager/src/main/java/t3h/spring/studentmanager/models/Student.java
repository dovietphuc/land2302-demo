package t3h.spring.studentmanager.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Student implements Serializable {
    private UUID id;
    private String name;
    private long dateOfBirth;
    private String gender;
    private String address;
}
