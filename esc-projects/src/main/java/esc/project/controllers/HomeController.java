package esc.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class HomeController {


	
	@RequestMapping("/")
	public String projectLandingPage()
	{
		return "projectIndex.html";
	}
	
	@RequestMapping("/projectOverview")
	public String projectOverview()
	{
		return "project_overview.html";
	}
	
	
	
	
	
}
