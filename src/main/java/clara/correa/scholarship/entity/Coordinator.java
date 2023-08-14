package clara.correa.scholarship.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="coordinators")
@RequiredArgsConstructor @Getter
public class Coordinator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCoord;
	
	@Setter
	private String nameCoord;
	
	@Setter
	@Email
	private String emailCoord;

	public Coordinator(String nameCoord, String emailCoord) {
		this.nameCoord = nameCoord;
		this.emailCoord = emailCoord;
	}
}
