package clara.correa.scholarship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter
public class InstructorDtoResponse {

	private Long idInstructor;
	private String nameInstructor;
	private String emailInstructor;
	
}
