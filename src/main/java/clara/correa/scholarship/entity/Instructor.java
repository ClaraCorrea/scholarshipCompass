package clara.correa.scholarship.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="instructors")
@Getter
@RequiredArgsConstructor
public class Instructor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Cascade({CascadeType.MERGE})
	private Long idInstructor;
	
	@Setter
	private String nameInstructor;
	
	@Setter
	@Email
	private String emailInstructor;
	
	public Instructor(String nameInstructor, String emailInstructor) {
		this.nameInstructor = nameInstructor;
		this.emailInstructor = emailInstructor;
	}
}

