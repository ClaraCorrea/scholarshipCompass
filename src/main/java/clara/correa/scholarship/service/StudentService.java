package clara.correa.scholarship.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.StudentDtoRequest;
import clara.correa.scholarship.dto.StudentDtoResponse;
import clara.correa.scholarship.entity.RelationStudentAffiliation;
import clara.correa.scholarship.entity.Student;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.repository.RelationStudentAffiliationRepository;
import clara.correa.scholarship.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private RelationStudentAffiliationRepository relationRepository;

	public CustomResponse saveStudent(StudentDtoRequest studentDtoRequest) {
	    Student student = new Student (
				studentDtoRequest.getNameStudent(),
				studentDtoRequest.getEmailStudent()
				);
		studentRepository.save(student);
	    return new CustomResponse(true, "Operação executada com sucesso!");
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
	
	@Transactional
	public CustomResponse deleteStudentById(Long studentId) {
	    Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Operation failed! Students not found"));
	    List<RelationStudentAffiliation> affiliationsWithStudent = relationRepository.findByStudent(student);

	    for (RelationStudentAffiliation affiliation : affiliationsWithStudent) {
	        relationRepository.delete(affiliation);
	    }

	    studentRepository.delete(student);
	    return new CustomResponse(true, "Operation executed successfully!");
	}

}

