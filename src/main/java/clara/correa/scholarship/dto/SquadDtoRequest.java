package clara.correa.scholarship.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SquadDtoRequest {
    private Long idSquad;
    private String nameSquad;
	@JsonDeserialize
    private List<Long> studentIds;
}
