package clara.correa.scholarship.dto;

import clara.correa.scholarship.entity.Affiliation;
import clara.correa.scholarship.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CombinedIdDto {
	private Student studentId;
	private Affiliation affiliationId;
}
