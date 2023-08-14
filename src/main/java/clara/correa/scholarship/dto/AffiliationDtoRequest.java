package clara.correa.scholarship.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import clara.correa.scholarship.entity.Coordinator;
import clara.correa.scholarship.entity.Instructor;
import clara.correa.scholarship.entity.ScrumMaster;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
public class AffiliationDtoRequest {


	private String nameAffiliation;
	private String statusAffiliation;
	@JsonDeserialize
	private Coordinator coordinatorAffiliation;
	@JsonDeserialize
	private ScrumMaster ScrumMasterAffiliation;
	@JsonDeserialize
	private Instructor instructorAffiliation;
}