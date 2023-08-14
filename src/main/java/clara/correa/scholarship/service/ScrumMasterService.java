package clara.correa.scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.ScrumMasterDtoRequest;
import clara.correa.scholarship.dto.ScrumMasterDtoResponse;
import clara.correa.scholarship.entity.ScrumMaster;
import clara.correa.scholarship.exception.CustomResponse;
import clara.correa.scholarship.repository.ScrumMasterRepository;

@Service
public class ScrumMasterService {

	@Autowired
	private ScrumMasterRepository scrumMasterRepository;
	
	public CustomResponse saveSM (ScrumMasterDtoRequest srumMasterDtoRequest) {
		ScrumMaster scrumMaster = new ScrumMaster(
				srumMasterDtoRequest.getNameSM(),
				srumMasterDtoRequest.getEmailSM()
				);
		scrumMasterRepository.save(scrumMaster);
	    return new CustomResponse(true, "Operação executada com sucesso!");
	}


	public ScrumMasterDtoResponse getByIdSM(Long idSM) {
		ScrumMaster scrumMasterGet = scrumMasterRepository.findById(idSM).orElseThrow();
		ScrumMasterDtoResponse scrumMasterDtoResponse = searchSM(scrumMasterGet);
		return scrumMasterDtoResponse;
	}

	private ScrumMasterDtoResponse searchSM(ScrumMaster scrumMasterGet) {
		ScrumMasterDtoResponse scrumMasterDtoResponse = new ScrumMasterDtoResponse(scrumMasterGet.getIdSM(), scrumMasterGet.getNameSM(), scrumMasterGet.getEmailSM());
		return scrumMasterDtoResponse;
	}
	
}

