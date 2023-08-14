package clara.correa.scholarship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor
public class StudentDtoResponse {

	private Long idStudent;
	private String nameStudent;
	private String emailStudent;
}
