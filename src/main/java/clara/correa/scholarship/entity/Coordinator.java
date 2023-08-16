package clara.correa.scholarship.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="coordinators")
@RequiredArgsConstructor 
@Getter
public class Coordinator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_coordinator")
	private Long idCoord;
	
	@Setter
	@Column(name="name_coordinator")
	private String nameCoord;
	
	@Setter
	@Email
	@Column(name="email_coordinator")
	private String emailCoord;

	public Coordinator(String nameCoord, String emailCoord) {
		this.nameCoord = nameCoord;
		this.emailCoord = emailCoord;
	}
}
