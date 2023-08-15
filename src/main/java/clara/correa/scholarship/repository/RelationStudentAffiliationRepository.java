package clara.correa.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clara.correa.scholarship.entity.RelationStudentAffiliation;

@Repository
public interface RelationStudentAffiliationRepository extends JpaRepository<RelationStudentAffiliation, Long>{

}
