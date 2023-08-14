package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import clara.correa.scholarship.dto.CoorditanorDtoRequest;
import clara.correa.scholarship.service.CoordinatorService;

@RestController
@RequestMapping("/coordinators")
public class CoordinatorController {
	@Autowired
	private CoordinatorService coordinatorService;
	
	@PostMapping("/post")
	public void postCoordinator(@RequestBody CoorditanorDtoRequest coordinatorDto) {
		coordinatorService.saveCoord(coordinatorDto);
	}
	
}
