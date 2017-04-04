package esc.project.services;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.models.Status;
import esc.project.repositories.StatusRepository;
import esc.project.repositories.StatusRepositoryCustom;

public class StatusRepositoryImpl implements StatusRepositoryCustom {

	@Autowired 
	StatusRepository statusRepo;
	
	@Override
	public void addNewStatus(String code, String text) {
		
		Status s = new Status();
		s.setCode(code);
		s.setText(text);
		statusRepo.save(s);
	}
	
	@Override
	public void deleteStatus(String code)
	{
		Status s = statusRepo.findByCode(code);
		if(null != s)
		{
			statusRepo.delete(s);
		}
		
	}

	
}
