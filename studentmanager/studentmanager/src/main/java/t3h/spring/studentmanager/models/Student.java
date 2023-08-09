package t3h.spring.studentmanager.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
public class Student {
    private UUID id;
    private String name;
    private long dateOfBirth;
    private String gender;
    private String address;
}
