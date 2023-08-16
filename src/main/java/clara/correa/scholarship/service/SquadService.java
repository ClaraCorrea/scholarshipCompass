package clara.correa.scholarship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.SquadDtoRequest;
import clara.correa.scholarship.entity.Squad;
import clara.correa.scholarship.entity.Student;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.repository.SquadRepository;
import clara.correa.scholarship.repository.StudentRepository;

@Service
public class SquadService {
    @Autowired
    private SquadRepository squadRepository;
    @Autowired
    private StudentRepository studentRepository;

    public CustomResponse saveSquad(SquadDtoRequest squadDtoRequest) {
    	int counter = 0;
        Squad squad = new Squad(
        		squadDtoRequest.getNameSquad(), 
        		null
        		);

        List<Long> studentIds = squadDtoRequest.getStudentIds();
        List<Long> studentIdsToSave = new ArrayList<>();

        for (Long studentId : studentIds) {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            optionalStudent.ifPresent(student -> studentIdsToSave.add(student.getIdStudent()));
            counter++;
        }
        
        if(counter > 5) {
            return new CustomResponse(false, "Operation failed! Please enter only 5 members or less in a squad!");
        } else {
            squad.setStudentsSquad(studentIdsToSave);
            squadRepository.save(squad);

            return new CustomResponse(true, "Operation executed successfully!");
        }
    }


}
