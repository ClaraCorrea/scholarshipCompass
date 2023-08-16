package clara.correa.scholarship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class StudentDtoRequest {
	private String nameStudent;
	private String emailStudent;
}
