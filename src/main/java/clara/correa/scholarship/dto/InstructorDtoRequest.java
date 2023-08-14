package clara.correa.scholarship.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter 
@Setter 
public class InstructorDtoRequest {

	private String nameInstructor;
	private String emailInstructor;
}
