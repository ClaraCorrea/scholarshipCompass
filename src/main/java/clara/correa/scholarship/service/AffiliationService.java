package clara.correa.scholarship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Coordinator coordinator = coordinatorRepository.findById(coordinatorId)
                .orElseThrow(() -> new EntityNotFoundException("Coordinator not found"));

        ScrumMaster scrumMaster = scrumMasterRepository.findById(scrumMasterId)
                .orElseThrow(() -> new EntityNotFoundException("ScrumMaster not found"));
        
    	int counter = 0;
        Affiliation affiliation = new Affiliation(
                affiliationDtoRequest.getNameAffiliation(),
                affiliationDtoRequest.getStatusAffiliation(),
                coordinator,
                scrumMaster,
                null
        );
        
        if(validationValues(affiliationDtoRequest)) {
        	return new CustomResponse(false, "The operation failed! Please enter valid values");
        } else {
            List<Long> instructorsIds = affiliationDtoRequest.getInstructorsAffiliation();
            List<Long> instructorsToSave = new ArrayList<>();

            for (Long instructorsId : instructorsIds) {
                Optional<Instructor> optionalStudent = instructorRepository.findById(instructorsId);
                optionalStudent.ifPresent(instructor -> instructorsToSave.add(instructor.getIdInstructor()));
                counter++;
            }
            
            if(counter > 3) {
                return new CustomResponse(false, "Operation failed! Please enter only 3 members in a class");
            } else {
                affiliation.setInstructorsAffiliation(instructorsToSave);;
                affiliationRepository.save(affiliation);

                return new CustomResponse(true, "Operation executed successfully!");
            }
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
		AffiliationDtoResponse affiliationDtoResponse = new AffiliationDtoResponse(affiliationGet.getIdAffiliation(), affiliationGet.getNameAffiliation(), affiliationGet.getStatusAffiliation(), affiliationGet.getCoordinatorAffiliation(), affiliationGet.getScrumMasterAffiliation(), affiliationGet.getInstructorsAffiliation());
		return affiliationDtoResponse;
	}
	
	public CustomResponse putAffiliation(Affiliation affiliation) {
        if (affiliationRepository.existsById(affiliation.getIdAffiliation())) {
            Affiliation existingAffiliation = affiliationRepository.findById(affiliation.getIdAffiliation()).get();
            
            if (validationStatusChange(affiliation, existingAffiliation)) {
                int studentCount = relationStudentAffiliationService.countStudentsByAffiliation(existingAffiliation);
                if (studentCount >= 15) {
                	if(validationRulesMet(affiliation)) {
                        affiliationRepository.save(affiliation);
                        return new CustomResponse(true, "Operation executed successfully!");
                	} else {
                		return new CustomResponse(true, "Operation Failed, please check if the submitted status is acceptable and if there are enough instructors registered in the class");
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

	private boolean validationRulesMet(Affiliation affiliation) {
	    if (affiliation.getStatusAffiliation().equalsIgnoreCase("started") ||
	            affiliation.getStatusAffiliation().equalsIgnoreCase("finished") ||
	            affiliation.getStatusAffiliation().equalsIgnoreCase("waiting")) {

	        List<Instructor> instructors = instructorRepository.findAllById(affiliation.getInstructorsAffiliation());
	        boolean hasExactlyThreeInstructors = instructors.size() >= 3;

	        return hasExactlyThreeInstructors;
	    }
	    return false;
	}


	private boolean validationStatusChange(Affiliation affiliation, Affiliation existingAffiliation) {
		return !existingAffiliation.getStatusAffiliation().equals(affiliation.getStatusAffiliation());
	}
}
