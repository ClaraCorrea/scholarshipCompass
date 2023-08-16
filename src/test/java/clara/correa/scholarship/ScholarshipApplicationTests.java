package clara.correa.scholarship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import clara.correa.scholarship.dto.AffiliationDtoRequest;
import clara.correa.scholarship.dto.AffiliationDtoResponse;
import clara.correa.scholarship.dto.CombinedIdDto;
import clara.correa.scholarship.dto.CoordinatorDtoResponse;
import clara.correa.scholarship.dto.CoorditanorDtoRequest;
import clara.correa.scholarship.dto.InstructorDtoRequest;
import clara.correa.scholarship.dto.InstructorDtoResponse;
import clara.correa.scholarship.dto.ScrumMasterDtoRequest;
import clara.correa.scholarship.dto.ScrumMasterDtoResponse;
import clara.correa.scholarship.dto.SquadDtoRequest;
import clara.correa.scholarship.dto.StudentDtoRequest;
import clara.correa.scholarship.entity.Affiliation;
import clara.correa.scholarship.entity.Coordinator;
import clara.correa.scholarship.entity.ScrumMaster;
import clara.correa.scholarship.entity.Student;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.repository.AffiliationRepository;
import clara.correa.scholarship.repository.CoordinatorRepository;
import clara.correa.scholarship.repository.RelationStudentAffiliationRepository;
import clara.correa.scholarship.repository.ScrumMasterRepository;
import clara.correa.scholarship.repository.StudentRepository;
import clara.correa.scholarship.service.AffiliationService;
import clara.correa.scholarship.service.CoordinatorService;
import clara.correa.scholarship.service.InstructorService;
import clara.correa.scholarship.service.RelationStudentAffiliationService;
import clara.correa.scholarship.service.ScrumMasterService;
import clara.correa.scholarship.service.SquadService;
import clara.correa.scholarship.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
class ScholarshipApplicationTests {

    @MockBean
    private AffiliationService affiliationService;
    @MockBean
    private SquadService squadService;
    @MockBean
    private CoordinatorService coordinatorService;    
    @MockBean
    private InstructorService instructorService;    
    @MockBean
    private StudentService studentService;
    @MockBean
    private RelationStudentAffiliationService relationService;
    @MockBean
    private ScrumMasterService scrumMasterService;


    @MockBean
    private CoordinatorRepository coordinatorRepository;
    @MockBean
    private ScrumMasterRepository scrumMasterRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AffiliationRepository affiliationRepository;
    @MockBean
    private RelationStudentAffiliationRepository relationRepository;

    @Test
    public void testPostAffiliation() {
        String nameAffiliation = "Class 1";
        String statusAffiliation = "waiting";

        String nameCoord = "Coord1";
        String emailCoord = "coord1@gmail.com";

        String nameSM = "SM1";
        String emailSM = "SM1@gmail.com";

        List<Integer> instructorsAffiliation = Arrays.asList(1, 2, 3);

        Coordinator coordinatorAffiliation = new Coordinator(
            nameCoord,
            emailCoord
        );

        ScrumMaster scrumMasterAffiliation = new ScrumMaster(
            nameSM,
            emailSM
        );

        AffiliationDtoRequest affiliation = new AffiliationDtoRequest(
            nameAffiliation,
            statusAffiliation,
            coordinatorAffiliation,
            scrumMasterAffiliation,
            instructorsAffiliation.stream().map(Long::valueOf).collect(Collectors.toList())
        );

        CustomResponse expected = new CustomResponse(true, "Operation executed successfully!");

        when(affiliationService.saveAffiliation(affiliation)).thenReturn(expected);

        CustomResponse result = affiliationService.saveAffiliation(affiliation);

        assertEquals(expected.isSuccess(), result.isSuccess());
        assertEquals(expected.getMessage(), result.getMessage());
    }
    
    @Test
    public void testPostSquad() {
        String nameSquad = "BugBusters";
        List<Integer> instructorsAffiliation = Arrays.asList(1, 2, 3);

        SquadDtoRequest squad = new SquadDtoRequest(
        	nameSquad,
            instructorsAffiliation.stream().map(Long::valueOf).collect(Collectors.toList())
        );

        CustomResponse expected = new CustomResponse(true, "Operation executed successfully!");

        when(squadService.saveSquad(squad)).thenReturn(expected);

        CustomResponse result = squadService.saveSquad(squad);

        assertEquals(expected.isSuccess(), result.isSuccess());
        assertEquals(expected.getMessage(), result.getMessage());
    }
    
    @Test
    public void testPostCoord() {
        String nameCoord = "Coord1";
        String emailCoord = "coord1@gmail.com";

        CoorditanorDtoRequest coordinator = new CoorditanorDtoRequest(
                nameCoord,
                emailCoord
            );

        CustomResponse expected = new CustomResponse(true, "Operation executed successfully!");

        when(coordinatorService.saveCoord(coordinator)).thenReturn(expected);

        CustomResponse result = coordinatorService.saveCoord(coordinator);

        assertEquals(expected.isSuccess(), result.isSuccess());
        assertEquals(expected.getMessage(), result.getMessage());
    }
    
    @Test
    public void testPostScrumMaster() {
        String nameSM = "SM1";
        String emailSM = "SM1@gmail.com";


        ScrumMasterDtoRequest scrumMaster = new ScrumMasterDtoRequest(
        		nameSM,
        		emailSM
            );

        CustomResponse expected = new CustomResponse(true, "Operation executed successfully!");

        when(scrumMasterService.saveSM(scrumMaster)).thenReturn(expected);

        CustomResponse result = scrumMasterService.saveSM(scrumMaster);

        assertEquals(expected.isSuccess(), result.isSuccess());
        assertEquals(expected.getMessage(), result.getMessage());
    }
    
    @Test
    public void testPostRelation() {
        MockitoAnnotations.openMocks(this);

        Student student = new Student();
        student.setIdStudent((long) 1);

        Affiliation affiliation = new Affiliation();
        affiliation.setIdAffiliation((long) 1);

        CombinedIdDto combinedIdDto = new CombinedIdDto(student, affiliation);

        when(studentRepository.findById(student.getIdStudent())).thenReturn(Optional.of(student));
        when(affiliationRepository.findById(affiliation.getIdAffiliation())).thenReturn(Optional.of(affiliation));
        when(relationRepository.findByAffiliation(affiliation)).thenReturn(new ArrayList<>());
        
        CustomResponse expected = new CustomResponse(true, "Operation executed successfully!");

        when(relationService.saveRelation(combinedIdDto)).thenReturn(expected);

        CustomResponse result = relationService.saveRelation(combinedIdDto);

        assertEquals(expected.isSuccess(), result.isSuccess());
        assertEquals(expected.getMessage(), result.getMessage());
    }
    
    @Test
    public void testPostInstructor(){
        String nameInstructor = "SM1";
        String emailInstructor = "SM1@gmail.com";


        InstructorDtoRequest instructor = new InstructorDtoRequest(
        		nameInstructor,
        		emailInstructor
            );

        CustomResponse expected = new CustomResponse(true, "Operation executed successfully!");

        when(instructorService.saveInstructor(instructor)).thenReturn(expected);

        CustomResponse result = instructorService.saveInstructor(instructor);

        assertEquals(expected.isSuccess(), result.isSuccess());
        assertEquals(expected.getMessage(), result.getMessage());
    }
    
    @Test
    public void testPostStudant(){
        String nameStudent = "Student1";
        String emailStudent = "student@gmail.com";


        StudentDtoRequest student = new StudentDtoRequest(
        		nameStudent,
        		emailStudent
            );

        CustomResponse expected = new CustomResponse(true, "Operation executed successfully!");

        when(studentService.saveStudent(student)).thenReturn(expected);

        CustomResponse result = studentService.saveStudent(student);

        assertEquals(expected.isSuccess(), result.isSuccess());
        assertEquals(expected.getMessage(), result.getMessage());
    }
    
    @Test
    public void testGetAffiliation() {
        Long idAffiliation = (long) 1;
        String nameAffiliation = "Spring Boot AWS";
        String statusAffiliation = "waiting";
        
        String nameCoord = "Coord1";
        String emailCoord = "coord1@gmail.com";
        
        List<Integer> instructorsAffiliation = Arrays.asList(1, 2, 3);

        String nameSM = "SM1";
        String emailSM = "SM1@gmail.com";
        
        Coordinator coordinatorAffiliation = new Coordinator(
                nameCoord,
                emailCoord
            );
        
        ScrumMaster scrumMasterAffiliation = new ScrumMaster(
        		nameSM,
        		emailSM
            );

        AffiliationDtoResponse expectedResponse = new AffiliationDtoResponse(
        		idAffiliation,
        		nameAffiliation,
        		statusAffiliation,
        		coordinatorAffiliation,
        		scrumMasterAffiliation,
        		instructorsAffiliation.stream().map(Long::valueOf).collect(Collectors.toList()));

        when(affiliationService.getByIdAffiliation(idAffiliation)).thenReturn(expectedResponse);

        AffiliationDtoResponse result = affiliationService.getByIdAffiliation(idAffiliation);
        
        assertEquals(expectedResponse.getIdAffiliation(), result.getIdAffiliation());
        assertEquals(expectedResponse.getNameAffiliation(), result.getNameAffiliation());
        assertEquals(expectedResponse.getStatusAffiliation(), result.getStatusAffiliation());
        assertEquals(expectedResponse.getCoordinatorAffiliation(), result.getCoordinatorAffiliation());
        assertEquals(expectedResponse.getScrumMasterAffiliation(), result.getScrumMasterAffiliation());
        assertEquals(expectedResponse.getInstructorsAffiliation(), result.getInstructorsAffiliation());
        
    }
    
    @Test
    public void testGetScrumMaster() {
        Long idSM = (long) 1;
        String nameSM = "SM1";
        String emailSM = "SM1@gmail.com";

        ScrumMasterDtoResponse expectedResponse = new ScrumMasterDtoResponse(idSM, nameSM, emailSM);

        when(scrumMasterService.getByIdSM(idSM)).thenReturn(expectedResponse);

        ScrumMasterDtoResponse result = scrumMasterService.getByIdSM(idSM);

        assertEquals(expectedResponse.getIdSM(), result.getIdSM());
        assertEquals(expectedResponse.getNameSM(), result.getNameSM());
        assertEquals(expectedResponse.getEmailSM(), result.getEmailSM());
    }
    
    @Test
    public void testGetCoord() {
        Long idCoord = (long) 1;
        String nameCoord = "SM1";
        String emailCoord = "SM1@gmail.com";

        CoordinatorDtoResponse expectedResponse = new CoordinatorDtoResponse(idCoord, nameCoord, emailCoord);

        when(coordinatorService.getByIdCoord(idCoord)).thenReturn(expectedResponse);

        CoordinatorDtoResponse result = coordinatorService.getByIdCoord(idCoord);

        assertEquals(expectedResponse.getIdCoord(), result.getIdCoord());
        assertEquals(expectedResponse.getNameCoord(), result.getNameCoord());
        assertEquals(expectedResponse.getEmailCoord(), result.getEmailCoord());
    }
    
    @Test
    public void testGetInstructor() {
        Long idInstructor = (long) 1;
        String nameInstructor = "SM1";
        String emailInstructor = "SM1@gmail.com";

        InstructorDtoResponse expectedResponse = new InstructorDtoResponse(idInstructor, nameInstructor, emailInstructor);

        when(instructorService.getByIdInstructor(idInstructor)).thenReturn(expectedResponse);

        InstructorDtoResponse result = instructorService.getByIdInstructor(idInstructor);

        assertEquals(expectedResponse.getIdInstructor(), result.getIdInstructor());
        assertEquals(expectedResponse.getNameInstructor(), result.getNameInstructor());
        assertEquals(expectedResponse.getEmailInstructor(), result.getEmailInstructor());
    }

}
