package clara.correa.scholarship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import clara.correa.scholarship.dto.AffiliationDtoRequest;
import clara.correa.scholarship.entity.Coordinator;
import clara.correa.scholarship.entity.Instructor;
import clara.correa.scholarship.entity.ScrumMaster;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.service.AffiliationService;

@SpringBootTest
class ScholarshipApplicationTests {

	@Test
	public void testExecutaAffiliation() {
	    String nameAffiliation = "Class 1";
	    String statusAffiliation = "waiting";
	    
	    String nameCoord = "Coord1";
	    String emailCoord = "coord1@gmail.com";
	    
	    String nameSM = "SM1";
	    String emailSM = "SM1@gmail.com";
	    
	    String nameInstructor = "Instructor1";
	    String emailInstructor = "Instructor1@gmail.com";
	    
	    
	    Coordinator coordinatorAffiliation = new Coordinator(
		        nameCoord,
		        emailCoord
		    );

	    ScrumMaster scrumMasterAffiliation = new ScrumMaster(
		        nameSM,
		        emailSM
		    );

	    Instructor instructorAffiliation = new Instructor(
	    		nameInstructor,
		        emailInstructor
		    );
	    
	    CustomResponse esperado = new CustomResponse(true, "Operação executada com sucesso!");
	    
	    AffiliationDtoRequest affiliation = new AffiliationDtoRequest(
	        nameAffiliation,
	        statusAffiliation,
	        coordinatorAffiliation,
	        scrumMasterAffiliation,
	        instructorAffiliation
	    );
	    
	    AffiliationService affiliationService = new AffiliationService();
	    CustomResponse resultado = affiliationService.saveAffiliation(affiliation);
	    
	    assertEquals(esperado, resultado);
	}

}
