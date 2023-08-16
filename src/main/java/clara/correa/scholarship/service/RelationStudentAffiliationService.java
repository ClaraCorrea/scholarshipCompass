package clara.correa.scholarship.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.CombinedIdDto;
import clara.correa.scholarship.entity.Affiliation;
import clara.correa.scholarship.entity.CombinedId;
import clara.correa.scholarship.entity.RelationStudentAffiliation;
import clara.correa.scholarship.entity.Student;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.repository.AffiliationRepository;
import clara.correa.scholarship.repository.RelationStudentAffiliationRepository;
import clara.correa.scholarship.repository.StudentRepository;

@Service
public class RelationStudentAffiliationService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AffiliationRepository affiliationRepository;
    @Autowired
    private RelationStudentAffiliationRepository relationRepository;

    public CustomResponse saveRelation(CombinedIdDto combinedIdDto) {
        CombinedId combinedId1 = new CombinedId(
        		combinedIdDto.getStudentId(), 
        		combinedIdDto.getAffiliationId()
        		);
        
    	int value = countStudentsByAffiliation(combinedIdDto.getAffiliationId());
        if (validateCombinedId(combinedId1)) {
        	if(value < 31) {
                RelationStudentAffiliation relation = new RelationStudentAffiliation();
                relation.setId(combinedId1);
                
                relationRepository.save(relation);            
                return new CustomResponse(true, "Operação executada com sucesso!");
        	} else {
        		return new CustomResponse(false, "Affiliation cheia!");
        	}
        } 
        return new CustomResponse(false, "IDs inválidos.");
    }

    public boolean validateCombinedId(CombinedId combinedId1) {
        Student student = combinedId1.getStudentId();
        Long studentId = student.getIdStudent(); 

        Affiliation affiliation = combinedId1.getAffiliationId();
        Long affiliationId = affiliation.getIdAffiliation();

        Optional<Student> studentValidation = studentRepository.findById(studentId);
        if (studentValidation.isEmpty()) {
            return false;
        }

        Optional<Affiliation> affiliationValidation = affiliationRepository.findById(affiliationId);
        if (affiliationValidation.isEmpty()) {
            return false;
        }
		return true;
    }
    
    public int countStudentsByAffiliation(Affiliation affiliation) {
        List<RelationStudentAffiliation> studentsWithAffiliation = relationRepository.findByAffiliation(affiliation);
        return studentsWithAffiliation.size();
    }
}

