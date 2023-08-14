package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clara.correa.scholarship.dto.InstructorDtoRequest;
import clara.correa.scholarship.dto.InstructorDtoResponse;
import clara.correa.scholarship.service.InstructorService;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/post")
	public void postInstructor(@RequestBody InstructorDtoRequest instructorDtoRequest) {
		instructorService.saveInstructor(instructorDtoRequest);
	}
	
	@GetMapping("/get/{idInstructor}")
	public InstructorDtoResponse getInstructor (@PathVariable Long idInstructor) {
		return instructorService.getByIdInstructor(idInstructor);
	}
}
