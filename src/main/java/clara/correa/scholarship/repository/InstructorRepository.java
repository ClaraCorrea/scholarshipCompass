package clara.correa.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clara.correa.scholarship.entity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}
