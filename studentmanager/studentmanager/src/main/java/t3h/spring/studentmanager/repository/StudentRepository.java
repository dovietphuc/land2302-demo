package t3h.spring.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import t3h.spring.studentmanager.entity.StudentEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

    @Query("SELECT s FROM StudentEntity s")
    public List<StudentEntity> getAllStudent();
}
