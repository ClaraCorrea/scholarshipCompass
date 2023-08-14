package clara.correa.scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clara.correa.scholarship.dto.AffiliationDtoRequest;
import clara.correa.scholarship.dto.AffiliationDtoResponse;
import clara.correa.scholarship.entity.Affiliation;
import clara.correa.scholarship.entity.Coordinator;
import clara.correa.scholarship.entity.Instructor;
import clara.correa.scholarship.entity.ScrumMaster;
import clara.correa.scholarship.repository.AffiliationRepository;
import clara.correa.scholarship.repository.CoordinatorRepository;
import clara.correa.scholarship.repository.InstructorRepository;
import clara.correa.scholarship.repository.ScrumMasterRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AffiliationService {
	
	@Autowired
	private AffiliationRepository affiliationRepository;
	@Autowired
	private CoordinatorRepository coordinatorRepository;
	@Autowired
	private ScrumMasterRepository scrumMasterRepository;
	@Autowired
	private InstructorRepository instructorRepository;

	@Transactional
	public String saveAffiliation2(AffiliationDtoRequest affiliationDtoRequest) {
	    Long coordinatorId = affiliationDtoRequest.getCoordinatorAffiliation().getIdCoord();
	    Long scrumMasterId = affiliationDtoRequest.getScrumMasterAffiliation().getIdSM();
	    Long instructorId = affiliationDtoRequest.getInstructorAffiliation().getIdInstructor();

	    Coordinator coordinator = coordinatorRepository.findById(coordinatorId)
	            .orElseThrow(() -> new EntityNotFoundException("Coordinator not found"));

	    ScrumMaster scrumMaster = scrumMasterRepository.findById(scrumMasterId)
	            .orElseThrow(() -> new EntityNotFoundException("ScrumMaster not found"));

	    Instructor instructor = instructorRepository.findById(instructorId)
	            .orElseThrow(() -> new EntityNotFoundException("Instructor not found"));

	    Affiliation affiliation = new Affiliation(
	        affiliationDtoRequest.getNameAffiliation(),
	        affiliationDtoRequest.getStatusAffiliation()
	    );

	    affiliation.setCoordinatorAffiliation(coordinator);
	    affiliation.setScrumMasterAffiliation(scrumMaster);
	    affiliation.setInstructorAffiliation(instructor);

	    affiliationRepository.save(affiliation);
	    String avise = "saved";
	    return avise;
	    
	}

	public AffiliationDtoResponse getByIdAffiliation(Long idAffiliation){
		Affiliation affiliationGet =  affiliationRepository.findById(idAffiliation).orElseThrow();
			AffiliationDtoResponse affiliationDtoResponse = searchAffiliation(affiliationGet);
			return affiliationDtoResponse;	
	}
	
	private AffiliationDtoResponse searchAffiliation(Affiliation affiliationGet) {
		AffiliationDtoResponse affiliationDtoResponse = new AffiliationDtoResponse(affiliationGet.getIdAffiliation(), affiliationGet.getNameAffiliation(), affiliationGet.getStatusAffiliation(), affiliationGet.getCoordinatorAffiliation(), affiliationGet.getScrumMasterAffiliation(), affiliationGet.getInstructorAffiliation());
		return affiliationDtoResponse;
	}
}

