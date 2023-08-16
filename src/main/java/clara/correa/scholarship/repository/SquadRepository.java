package clara.correa.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clara.correa.scholarship.entity.Squad;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long>{

}
