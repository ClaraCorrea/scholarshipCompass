package clara.correa.scholarship.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import clara.correa.scholarship.entity.Affiliation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StudentDtoRequest {

	private String nameStudent;
	private String emailStudent;
	@JsonDeserialize
	public Affiliation getAffliationStudent;
}
