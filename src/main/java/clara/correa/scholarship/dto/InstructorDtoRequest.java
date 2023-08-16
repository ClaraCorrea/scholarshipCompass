package clara.correa.scholarship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter 
@Setter 
@AllArgsConstructor
public class InstructorDtoRequest {

	private String nameInstructor;
	private String emailInstructor;
}
