package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clara.correa.scholarship.dto.ScrumMasterDtoRequest;
import clara.correa.scholarship.service.ScrumMasterService;

@RestController
@RequestMapping("/scrum_masters")
public class ScrumMasterController {
	
	@Autowired
	private ScrumMasterService scrumMasterService;
	
	@PostMapping("/post")
	public void postCoordinator(@RequestBody ScrumMasterDtoRequest scrumMasterDto) {
		scrumMasterService.saveSM(scrumMasterDto);
	}
}
