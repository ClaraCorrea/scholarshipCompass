package clara.correa.scholarship.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter 
@Setter
public class ScrumMasterDtoRequest {
	
	@NotBlank
	private String nameSM;
	
	@NotBlank
	private String emailSM;
}
