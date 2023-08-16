package clara.correa.scholarship.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "squads")
@Getter
@Setter
@AllArgsConstructor
public class Squad {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_squad")
    private Long idSquad;

    @Column(name = "name_squad")
    private String nameSquad;

    @ElementCollection
    @CollectionTable(name = "squad_students", joinColumns = @JoinColumn(name = "squad_id"))
    @Column(name = "student_id")
    private List<Long> studentsSquad;
    
	public Squad(String nameSquad, List<Long> studentsSquad) {
		this.nameSquad = nameSquad;
		this.studentsSquad = studentsSquad;
	}
	
    public void setStudentsSquad(List<Long> studentsSquad) {
        this.studentsSquad = studentsSquad;
    }
}
