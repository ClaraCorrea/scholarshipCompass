package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clara.correa.scholarship.dto.SquadDtoRequest;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.service.SquadService;

@RestController
@RequestMapping("/squads")
public class SquadController {

	@Autowired
	private SquadService squadService;
	
	@PostMapping("/post")
	public ResponseEntity<CustomResponse> postSquad(@RequestBody SquadDtoRequest squadDtoRequest) {
		CustomResponse response = squadService.saveSquad(squadDtoRequest);
        return ResponseEntity.ok(response);
	}
}
