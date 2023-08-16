package clara.correa.scholarship.entity;

import java.util.List;

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
    private Coordinator coordinatorAffiliation;

    @OneToOne
    @JsonIgnoreProperties("scrumMasterAffiliation")
    private ScrumMaster scrumMasterAffiliation;

    @ElementCollection
    @Column(name = "instructor_id")
    private List<Long> instructorsAffiliation;


	
	public Affiliation(String nameAffiliation, String statusAffiliation, Coordinator coordinatorAffiliation, ScrumMaster scrumMasterAffiliation, List<Long> instructorsAffiliation) {
		this.nameAffiliation = nameAffiliation;
		this.statusAffiliation = statusAffiliation;	
		this.coordinatorAffiliation = coordinatorAffiliation;
		this.scrumMasterAffiliation = scrumMasterAffiliation;
		this.instructorsAffiliation = instructorsAffiliation;
	}
	
    public void setStudentsSquad(List<Long> instructorsAffiliations) {
        this.instructorsAffiliation = instructorsAffiliations;
    }
}