package clara.correa.scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.StudentDtoRequest;
import clara.correa.scholarship.dto.StudentDtoResponse;
import clara.correa.scholarship.entity.Student;
import clara.correa.scholarship.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	public void saveStudent(StudentDtoRequest studentDtoRequest) {
		Student student = new Student (
				studentDtoRequest.getNameStudent(),
				studentDtoRequest.getEmailStudent()
				);
		studentRepository.save(student);
	}

	public StudentDtoResponse getByIdStudent(Long idStudent) {
		Student studentGet = studentRepository.findById(idStudent).orElseThrow();
		StudentDtoResponse studentDtoResponse = searchStudent(studentGet);
		return studentDtoResponse;
	}

	private StudentDtoResponse searchStudent(Student studentGet) {
		StudentDtoResponse studentDtoResponse = new StudentDtoResponse(studentGet.getIdStudent(), studentGet.getNameStudent(), studentGet.getEmailStudent());
		return studentDtoResponse;
	}
}

