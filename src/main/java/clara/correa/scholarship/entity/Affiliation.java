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
@Setter
public class Affiliation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_affiliation")
	private Long idAffiliation;
	
	@Column(name="name_affiliation")
	private String nameAffiliation;

	@Column(name="status_affiliation")
	private String statusAffiliation;

    @OneToOne
    @JsonIgnoreProperties("coordinatorAffiliation")
    @Cascade({CascadeType.MERGE})
    @PrimaryKeyJoinColumn
	private Coordinator coordinatorAffiliation;

    @OneToOne
    @JsonIgnoreProperties("scrumMasterAffiliation") 
    @Cascade({CascadeType.MERGE})
    @JoinColumn(name = "scrumMaster_idAffiliation")
	private ScrumMaster scrumMasterAffiliation;

    @OneToOne
    @JsonIgnoreProperties("instructorAffiliation")
    @Cascade({CascadeType.MERGE})
    @JoinColumn(name = "instructor_idAffiliation")
	private Instructor instructorAffiliation;
	
	public Affiliation(String nameAffiliation, String statusAffiliation, Coordinator coordinatorAffiliation, ScrumMaster scrumMasterAffiliation, Instructor instructorAffiliation) {
		this.nameAffiliation = nameAffiliation;
		this.statusAffiliation = statusAffiliation;	
		this.coordinatorAffiliation = coordinatorAffiliation;
		this.scrumMasterAffiliation = scrumMasterAffiliation;
		this.instructorAffiliation = instructorAffiliation;
	}
}