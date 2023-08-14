package clara.correa.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clara.correa.scholarship.entity.Coordinator;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long>{
	
	
}

