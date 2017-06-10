package esc.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.models.Project;
import esc.project.models.RequiredResource;
import esc.project.repositories.ProjectRepository;
import esc.project.repositories.RequiredResourceRepository;
import esc.project.services.RequiredResourceRepositoryImpl;

@RestController
public class RequiredResourceController {

	@Autowired
	RequiredResourceRepositoryImpl reqResourceRepo;
	
	@Autowired
	RequiredResourceRepository reqResRep;
	
	@Autowired
	ProjectRepository projRepo;
	
	
	@RequestMapping(value="/getAllRRs", method=RequestMethod.GET)
	public List<RequiredResource> getResourcesOnProject(@RequestParam("projID") String projID)
	{
		Project p = projRepo.findById(Integer.parseInt(projID));
		return reqResourceRepo.getAllRRs(p);
	}
	
	@RequestMapping(value="/saveRR", method=RequestMethod.POST)
	public void addRRToProj(@RequestParam("projID") String projID, @RequestParam("title") String title,  @RequestParam("quantity") String quantity, @RequestParam("price") String price, @RequestParam(value="note", required=false) String note)
	{
		Project p = projRepo.findById(Integer.parseInt(projID));
		RequiredResource rr = new RequiredResource();
		if(note != null)
			rr.setNote(note);
		rr.setProject(p);
		rr.setQuantity(Integer.parseInt(quantity));
		rr.setTitle(title);
		rr.setUnitPrice(Double.parseDouble(price));
		
		reqResRep.save(rr);
		
	}
}
