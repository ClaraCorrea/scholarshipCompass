package clara.correa.scholarship.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="scrum_masters")
@Getter 
@RequiredArgsConstructor
public class ScrumMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Cascade({CascadeType.MERGE})
	@Column(name="id_scrumMaster")
	private Long idSM;
	
	@Setter
	@Column(name="name_scrumMaster")
	private String nameSM;
	
	@Setter
	@Email
	@Column(name="email_scrumMaster")
	private String emailSM;

	public ScrumMaster(String nameSM, String emailSM) {
		this.nameSM = nameSM;
		this.emailSM = emailSM;
	}
}
