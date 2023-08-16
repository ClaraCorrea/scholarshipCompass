package clara.correa.scholarship.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clara.correa.scholarship.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findById(Long studentId);
}