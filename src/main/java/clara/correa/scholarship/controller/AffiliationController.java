package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clara.correa.scholarship.dto.AffiliationDtoRequest;
import clara.correa.scholarship.dto.AffiliationDtoResponse;
import clara.correa.scholarship.entity.Affiliation;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.service.AffiliationService;

@RestController
@RequestMapping("/affiliations")
public class AffiliationController {

	@Autowired
	private AffiliationService affiliationService;
	
	@PostMapping("/post")
	public ResponseEntity<CustomResponse> saveAffiliation(@RequestBody AffiliationDtoRequest affiliationDtoRequest) {
		CustomResponse response = affiliationService.saveAffiliation(affiliationDtoRequest);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/get/{idAffiliation}")
	public AffiliationDtoResponse getAffiliation(@PathVariable Long idAffiliation) {
		return affiliationService.getByIdAffiliation(idAffiliation);
	}
	
    @PutMapping("/put/{idAffiliation}")
    public ResponseEntity<CustomResponse> putAffiliation(@PathVariable Long idAffiliation, @RequestBody Affiliation updatedAffiliation) {
        updatedAffiliation.setIdAffiliation(idAffiliation); 
        CustomResponse response = affiliationService.putAffiliation(updatedAffiliation);
        return ResponseEntity.ok(response);
    }
}