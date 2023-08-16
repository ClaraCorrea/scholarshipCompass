package clara.correa.scholarship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import clara.correa.scholarship.dto.AffiliationDtoRequest;
import clara.correa.scholarship.entity.Coordinator;
import clara.correa.scholarship.entity.ScrumMaster;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.repository.CoordinatorRepository;
import clara.correa.scholarship.service.AffiliationService;

@SpringBootTest
@AutoConfigureMockMvc
class ScholarshipApplicationTests {

    @MockBean
    private AffiliationService affiliationService;

    @MockBean
    private CoordinatorRepository coordinatorRepository;

    @Test
    public void testExecutaAffiliation() {
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

}
