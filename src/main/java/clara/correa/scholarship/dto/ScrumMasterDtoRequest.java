package clara.correa.scholarship.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
public class ScrumMasterDtoRequest {
	@NotBlank
	private String nameSM;
	
	@NotBlank
	private String emailSM;
}
