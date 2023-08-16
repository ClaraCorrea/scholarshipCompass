package clara.correa.scholarship.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter 
@Setter 
@AllArgsConstructor
public class CoorditanorDtoRequest {
	
	@NotBlank
	private String nameCoord;
	
	@NotBlank
	private String emailCoord;
}
