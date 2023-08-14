package clara.correa.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clara.correa.scholarship.entity.Affiliation;

@Repository
public interface AffiliationRepository extends JpaRepository<Affiliation, Long>{

}
