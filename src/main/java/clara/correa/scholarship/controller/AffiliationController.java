package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clara.correa.scholarship.dto.AffiliationDtoRequest;
import clara.correa.scholarship.dto.AffiliationDtoResponse;
import clara.correa.scholarship.service.AffiliationService;

@RestController
@RequestMapping("/affiliations")
public class AffiliationController {

	@Autowired
	private AffiliationService affiliationService;
	
	@PostMapping("/post")
	public void saveAffiliation(@RequestBody AffiliationDtoRequest affiliationDtoRequest) {
		affiliationService.saveAffiliation2(affiliationDtoRequest);
	}
	
	@GetMapping("/get/{idAffiliation}")
	public AffiliationDtoResponse getAffiliation(@PathVariable Long idAffiliation) {
		return affiliationService.getByIdAffiliation(idAffiliation);
	}
}