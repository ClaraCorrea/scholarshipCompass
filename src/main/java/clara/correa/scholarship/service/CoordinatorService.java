package clara.correa.scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.CoordinatorDtoResponse;
import clara.correa.scholarship.dto.CoorditanorDtoRequest;
import clara.correa.scholarship.entity.Coordinator;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.repository.CoordinatorRepository;

@Service
public class CoordinatorService {

	@Autowired
	private CoordinatorRepository coordinatorRepository;

	public CustomResponse saveCoord(CoorditanorDtoRequest coordinatorDtoRequest) {
		Coordinator coordinator = new Coordinator(
				coordinatorDtoRequest.getNameCoord(),
				coordinatorDtoRequest.getEmailCoord()	
				);
		coordinatorRepository.save(coordinator);
	    return new CustomResponse(true, "Operação executada com sucesso!");
	}
	
	public CoordinatorDtoResponse getByIdCoord(Long idCoord){
		Coordinator coordinatorGet =  coordinatorRepository.findById(idCoord).orElseThrow();
			CoordinatorDtoResponse coordinatorDtoResponse = searchCoord(coordinatorGet);
			return coordinatorDtoResponse;	
	}
	
	private CoordinatorDtoResponse searchCoord(Coordinator coordinatorGet) {
		CoordinatorDtoResponse coordinatorDtoResponse = new CoordinatorDtoResponse(coordinatorGet.getIdCoord(), coordinatorGet.getNameCoord(), coordinatorGet.getEmailCoord());
		return coordinatorDtoResponse;
	}
}
