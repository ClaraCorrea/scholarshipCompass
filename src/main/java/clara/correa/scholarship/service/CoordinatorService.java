package clara.correa.scholarship.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.CoordinatorDtoResponse;
import clara.correa.scholarship.dto.CoorditanorDtoRequest;
import clara.correa.scholarship.entity.Coordinator;
import clara.correa.scholarship.entity.Instructor;
import clara.correa.scholarship.entity.ScrumMaster;
import clara.correa.scholarship.entity.Student;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.repository.CoordinatorRepository;
import clara.correa.scholarship.repository.InstructorRepository;
import clara.correa.scholarship.repository.ScrumMasterRepository;
import clara.correa.scholarship.repository.StudentRepository;

@Service
public class CoordinatorService {

	@Autowired
	private CoordinatorRepository coordinatorRepository;
	@Autowired
	private ScrumMasterRepository scrumMasterRepository;
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private StudentRepository studentRepository;

	public CustomResponse saveCoord(CoorditanorDtoRequest coordinatorDtoRequest) {
		Coordinator coordinator = new Coordinator(
				coordinatorDtoRequest.getNameCoord(),
				coordinatorDtoRequest.getEmailCoord()	
				);
		coordinatorRepository.save(coordinator);
	    return new CustomResponse(true, "Operation executed successfully!");
	}
	
	public CoordinatorDtoResponse getByIdCoord(Long idCoord){
		Coordinator coordinatorGet =  coordinatorRepository.findById(idCoord).orElseThrow();
			CoordinatorDtoResponse coordinatorDtoResponse = searchCoord(coordinatorGet);
			return coordinatorDtoResponse;	
	}
	
	private CoordinatorDtoResponse searchCoord(Coordinator coordinatorGet) {
		CoordinatorDtoResponse coordinatorDtoResponse = new CoordinatorDtoResponse(coordinatorGet.getIdCoord(), coordinatorGet.getNameCoord(), coordinatorGet.getEmailCoord());
		return coordinatorDtoResponse;
	}
	
	public CustomResponse postAll() throws FileNotFoundException, IOException {
		List<Coordinator> coordinators = new ArrayList<>();
        List<ScrumMaster> scrumMasters = new ArrayList<>();
        List<Instructor> instructors = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        
        File dataSource = new File("src\\main\\resources\\dataSource.txt");
        if (!dataSource.exists()) {
            return new CustomResponse(false, "File not found!");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dataSource))) {
            String line;
            String currentSection = null;
			while ((line = reader.readLine()) != null) {
	            if (line.endsWith(":")) {
	                currentSection = line.substring(0, line.length() - 1);
	            } else if ("Coordinator".equals(currentSection)) {
	                String[] parts = line.split(",");
	                if (parts.length == 2) {
	                    String name = parts[0].trim();
	                    String email = parts[1].trim();
	                    coordinators.add(new Coordinator(name, email));
	                    coordinatorRepository.saveAll(coordinators);
	                }
	            } else if ("Scrum_Master".equals(currentSection)) {
	                String[] parts = line.split(",");
	                if (parts.length == 2) {
	                    String name = parts[0].trim();
	                    String email = parts[1].trim();
	                    scrumMasters.add(new ScrumMaster(name, email));
	                    scrumMasterRepository.saveAll(scrumMasters);
	                    }
	                } else if ("Instructor".equals(currentSection)) {
	                    String[] parts = line.split(",");
	                    if (parts.length == 2) {
	                        String name = parts[0].trim();
	                        String email = parts[1].trim();
	                        instructors.add(new Instructor(name, email));
	                        instructorRepository.saveAll(instructors);
	                    }
	                } else if ("Student".equals(currentSection)) {
	                    String[] parts = line.split(",");
	                    if (parts.length == 2) {
	                        String name = parts[0].trim();
	                        String email = parts[1].trim();
	                        students.add(new Student(name, email));
	                        studentRepository.saveAll(students);
	                    }
	                }
	            } 
        	} catch  (IOException e) {
	          	return new CustomResponse(false, "Error reading the file");
        	} 
	    	if (coordinators.isEmpty() && scrumMasters.isEmpty() && instructors.isEmpty() && students.isEmpty()) {
	            return new CustomResponse(false, "Empty file or incorrect format");
	        } 
	    	return new CustomResponse(true, "Operation executed successfully!");

	}
}





