package clara.correa.scholarship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import clara.correa.scholarship.entity.Affiliation;
import clara.correa.scholarship.entity.RelationStudentAffiliation;

@Repository
public interface RelationStudentAffiliationRepository extends JpaRepository<RelationStudentAffiliation, Long>{
	@Query("SELECT r FROM RelationStudentAffiliation r WHERE r.id.affiliationId = :affiliation")
	List<RelationStudentAffiliation> findByAffiliation(Affiliation affiliation);
}
