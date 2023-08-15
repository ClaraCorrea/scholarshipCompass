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
	@Column(name="id_instructor")
	private Long idInstructor;
	
	@Setter
	@Column(name="name_instructor")
	private String nameInstructor;
	
	@Setter
	@Email
	@Column(name="email_instructor")
	private String emailInstructor;
	
	public Instructor(String nameInstructor, String emailInstructor) {
		this.nameInstructor = nameInstructor;
		this.emailInstructor = emailInstructor;
	}
}

