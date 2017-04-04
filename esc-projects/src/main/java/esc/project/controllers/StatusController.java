package esc.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.services.StatusRepositoryImpl;

@RestController
public class StatusController {

	@Autowired
	private StatusRepositoryImpl statusRepo;
	

	
	@RequestMapping("/addNewStatus")
	public void newStatus (@RequestParam(value="code") String code, @RequestParam(value="text") String text)
	{
		statusRepo.addNewStatus(code, text);
	}
	
	@RequestMapping("/deleteStatus")
	public void deleteStatus(@RequestParam(value="code") String code)
	{
		statusRepo.deleteStatus(code);
	}
	
}
