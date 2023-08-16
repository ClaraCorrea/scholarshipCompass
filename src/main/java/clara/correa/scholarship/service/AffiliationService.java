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
import clara.correa.scholarship.exception.CustomResponse;
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
	@Autowired
	private RelationStudentAffiliationService relationStudentAffiliationService;

	@Transactional
	public CustomResponse saveAffiliation(AffiliationDtoRequest affiliationDtoRequest) {

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
	        affiliationDtoRequest.getStatusAffiliation(),
	        coordinator,
	        scrumMaster,
	        instructor
	    );
	    
	    if (validationValues(affiliationDtoRequest)) {
	    	return new CustomResponse(false, "Operation Failed, please check the registered value!");
	    } else {
		    affiliationRepository.save(affiliation);
		    return new CustomResponse(true, "Operation executed successfully!");
	    }
	}

	private boolean validationValues(AffiliationDtoRequest affiliationDtoRequest) {
		return affiliationDtoRequest.getNameAffiliation().isBlank() ||  !affiliationDtoRequest.getStatusAffiliation().equalsIgnoreCase("waiting");
	}
	

	public AffiliationDtoResponse getByIdAffiliation(Long idAffiliation){
		Affiliation affiliationGet =  affiliationRepository.findByIdAffiliation(idAffiliation).orElseThrow(() -> new EntityNotFoundException("Class not found, please cheack the registered id!"));
			AffiliationDtoResponse affiliationDtoResponse = searchAffiliation(affiliationGet);
			return affiliationDtoResponse;	
	}
	
	private AffiliationDtoResponse searchAffiliation(Affiliation affiliationGet) {
		AffiliationDtoResponse affiliationDtoResponse = new AffiliationDtoResponse(affiliationGet.getIdAffiliation(), affiliationGet.getNameAffiliation(), affiliationGet.getStatusAffiliation(), affiliationGet.getCoordinatorAffiliation(), affiliationGet.getScrumMasterAffiliation(), affiliationGet.getInstructorAffiliation());
		return affiliationDtoResponse;
	}
	
	public CustomResponse putAffiliation(Affiliation affiliation) {
        if (affiliationRepository.existsById(affiliation.getIdAffiliation())) {
            Affiliation existingAffiliation = affiliationRepository.findById(affiliation.getIdAffiliation()).get();
            
            if (validationStatusChange(affiliation, existingAffiliation)) {
                int studentCount = relationStudentAffiliationService.countStudentsByAffiliation(existingAffiliation);
                if (studentCount >= 15) {
                	if(validationStatusValue(affiliation)) {
                        affiliationRepository.save(affiliation);
                        return new CustomResponse(true, "Operation executed successfully!");
                	} else {
                		return new CustomResponse(true, "Please enter a valid status! (waiting, started or finished)!");
                	}
                } else {
                    return new CustomResponse(false, "Cannot change status. Less than 15 students are linked to this affiliation.");
                }
            } else {
                affiliationRepository.save(affiliation);
                return new CustomResponse(true, "Operation executed successfully!");
            }
        }
        return new CustomResponse(false, "Operation Failed, please check the registered value!");
    }

	private boolean validationStatusValue(Affiliation affiliation) {
		return affiliation.getStatusAffiliation().equalsIgnoreCase("started") || affiliation.getStatusAffiliation().equalsIgnoreCase("finished") || affiliation.getStatusAffiliation().equalsIgnoreCase("waiting");
	}

	private boolean validationStatusChange(Affiliation affiliation, Affiliation existingAffiliation) {
		return !existingAffiliation.getStatusAffiliation().equals(affiliation.getStatusAffiliation());
	}

}

