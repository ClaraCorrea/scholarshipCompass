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
	private Long idSM;
	
	@Setter
	private String nameSM;
	
	@Setter
	@Email
	private String emailSM;

	public ScrumMaster(String nameSM, String emailSM) {
		this.nameSM = nameSM;
		this.emailSM = emailSM;
	}
}
