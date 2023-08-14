package clara.correa.scholarship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clara.correa.scholarship.dto.ScrumMasterDtoRequest;
import clara.correa.scholarship.entity.ScrumMaster;
import clara.correa.scholarship.repository.ScrumMasterRepository;

@Service
public class ScrumMasterService {

	@Autowired
	private ScrumMasterRepository scrumMasterRepository;
	
	public void saveSM (ScrumMasterDtoRequest srumMasterDtoRequest) {
		ScrumMaster scrumMaster = new ScrumMaster(
				srumMasterDtoRequest.getNameSM(),
				srumMasterDtoRequest.getEmailSM()
				);
		scrumMasterRepository.save(scrumMaster);
	}	
}

