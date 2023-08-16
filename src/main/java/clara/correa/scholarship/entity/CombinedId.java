package clara.correa.scholarship.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CombinedId implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
    @JoinColumn(name = "student_id")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "affiliation_id")
    private Affiliation affiliationId;
}

