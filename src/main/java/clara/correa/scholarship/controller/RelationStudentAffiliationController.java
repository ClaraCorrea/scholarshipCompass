package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clara.correa.scholarship.dto.CombinedIdDto;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.service.RelationStudentAffiliationService;

@RestController
@RequestMapping("/conection_affiliation_student")
public class RelationStudentAffiliationController {
	
	@Autowired
	RelationStudentAffiliationService relationStudentAffiliationControllerService;
	
	@PostMapping("/post")
	public ResponseEntity<CustomResponse> postCoordinator(@RequestBody CombinedIdDto combinedIdDto) {
		CustomResponse response = relationStudentAffiliationControllerService.saveRelation(combinedIdDto);
        return ResponseEntity.ok(response);
	}
}
