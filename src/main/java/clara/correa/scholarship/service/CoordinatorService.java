package clara.correa.scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.CoorditanorDtoRequest;
import clara.correa.scholarship.entity.Coordinator;
import clara.correa.scholarship.repository.CoordinatorRepository;

@Service
public class CoordinatorService {

	@Autowired
	private CoordinatorRepository coordinatorRepository;

	public void saveCoord(CoorditanorDtoRequest coordinatorDtoRequest) {
		Coordinator coordinator = new Coordinator(
				coordinatorDtoRequest.getNameCoord(),
				coordinatorDtoRequest.getEmailCoord()	
				);
		coordinatorRepository.save(coordinator);
	}
}
