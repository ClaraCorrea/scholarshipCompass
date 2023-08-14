package clara.correa.scholarship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clara.correa.scholarship.entity.ScrumMaster;

@Repository
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long>{

}
