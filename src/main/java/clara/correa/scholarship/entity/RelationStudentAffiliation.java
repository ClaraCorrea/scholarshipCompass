package clara.correa.scholarship.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="RelationStudentAffiliation")
@Getter
@Setter
public class RelationStudentAffiliation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relation")
	private CombinedId id;

}
