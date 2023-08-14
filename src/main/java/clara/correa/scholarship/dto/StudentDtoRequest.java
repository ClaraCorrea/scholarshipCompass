package clara.correa.scholarship.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StudentDtoRequest {

	private String nameStudent;
	private String emailStudent;
}
