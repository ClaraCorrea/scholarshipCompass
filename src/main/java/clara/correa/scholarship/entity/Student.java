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
	@Column(name="id_student")
	private Long idStudent;
	
	@Setter
	@Column(name="name_student")
	private String nameStudent;
	
	@Email
	@Setter
	@Column(name="email_student")
	private String emailStudent;

	public Student(String nameStudent, String emailStudent) {
		this.nameStudent = nameStudent;
		this.emailStudent = emailStudent;
	}
}

