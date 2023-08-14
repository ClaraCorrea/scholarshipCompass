package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clara.correa.scholarship.dto.ScrumMasterDtoRequest;
import clara.correa.scholarship.dto.ScrumMasterDtoResponse;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.service.ScrumMasterService;

@RestController
@RequestMapping("/scrum_masters")
public class ScrumMasterController {
	
	@Autowired
	private ScrumMasterService scrumMasterService;
	
	@PostMapping("/post")
	public ResponseEntity<CustomResponse> postCoordinator(@RequestBody ScrumMasterDtoRequest scrumMasterDto) {
		CustomResponse response =  scrumMasterService.saveSM(scrumMasterDto);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/get/{idSM}")
	public ScrumMasterDtoResponse getScrumMaster (@PathVariable Long idSM) {
		return scrumMasterService.getByIdSM(idSM);
	}
	
}
