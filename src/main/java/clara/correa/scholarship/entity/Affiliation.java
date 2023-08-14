package clara.correa.scholarship.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="affiliations")
public class Affiliation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAffiliation;
	
	@Setter
	private String nameAffiliation;

	@Setter
	private String statusAffiliation;
	
	@Setter
	@OneToOne
	@JsonIgnoreProperties("Coordinator")
	@Cascade({CascadeType.MERGE})
	private Coordinator coordinatorAffiliation;
	
	@Setter
	@OneToOne
	@JsonIgnoreProperties("ScrumMaster")
	@Cascade({CascadeType.MERGE})
	private ScrumMaster scrumMasterAffiliation;
	
	@Setter
	@OneToOne
	@JsonIgnoreProperties("Instructor")
	@Cascade({CascadeType.MERGE})
	private Instructor instructorAffiliation;
	
	public Affiliation(String nameAffiliation, String statusAffiliation) {
		this.nameAffiliation = nameAffiliation;
		this.statusAffiliation = statusAffiliation;	
	}
}