package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import clara.correa.scholarship.dto.CoordinatorDtoResponse;
import clara.correa.scholarship.dto.CoorditanorDtoRequest;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.service.CoordinatorService;

@RestController
@RequestMapping("/coordinators")
public class CoordinatorController {
	@Autowired
	private CoordinatorService coordinatorService;
	
	@PostMapping("/post")
	public ResponseEntity<CustomResponse> postCoordinator(@RequestBody CoorditanorDtoRequest coordinatorDto) {
		CustomResponse response = coordinatorService.saveCoord(coordinatorDto);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/get/{idCoord}")
	public CoordinatorDtoResponse getCoordinator (@PathVariable Long idCoord) {
		return coordinatorService.getByIdCoord(idCoord);
	}
}
