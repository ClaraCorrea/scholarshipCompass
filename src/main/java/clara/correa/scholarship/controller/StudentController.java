package clara.correa.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clara.correa.scholarship.dto.StudentDtoRequest;
import clara.correa.scholarship.dto.StudentDtoResponse;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/post")
	private ResponseEntity<CustomResponse> saveStudent(@RequestBody StudentDtoRequest studentDtoRequest) {
		CustomResponse response = studentService.saveStudent(studentDtoRequest);
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/get/{idStudent}")
	public StudentDtoResponse getStudent (@PathVariable Long idStudent){
		return studentService.getByIdStudent(idStudent);
	}
	
    @DeleteMapping("delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
            studentService.deleteStudentById(studentId);

    }
}
