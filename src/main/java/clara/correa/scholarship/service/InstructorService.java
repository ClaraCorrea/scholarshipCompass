package clara.correa.scholarship.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.InstructorDtoRequest;
import clara.correa.scholarship.entity.Instructor;
import clara.correa.scholarship.repository.InstructorRepository;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepository instructorRepository;

	public void saveInstructor(InstructorDtoRequest instructorDtoRequest) {
		Instructor instructor = new Instructor(
				instructorDtoRequest.getNameInstructor(),
				instructorDtoRequest.getEmailInstructor()
				);
		instructorRepository.save(instructor);
	}
}
