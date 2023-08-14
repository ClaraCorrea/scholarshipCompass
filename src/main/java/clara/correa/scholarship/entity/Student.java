package clara.correa.scholarship.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="students")
@Getter
@RequiredArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStudent;
	
	@Setter
	private String nameStudent;
	
	@Email
	@Setter
	private String emailStudent;

	public Student(String nameStudent, String emailStudent) {
		this.nameStudent = nameStudent;
		this.emailStudent = emailStudent;
	}
}

