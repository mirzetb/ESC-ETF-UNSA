package com.project.nwt.controller;





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {
	
	
	
	@RequestMapping(value= "/")
	public String landingPage()
	{
		return "index.html";
	}
	
	@RequestMapping(value="/profile")
	public String profilePage()
	{
		
		return "UserPage.html";
	}
	@RequestMapping(value="/adminPanel")
	public String adminPanel()
	{
		return "admin_panel.html";
	}
	
	
	
	
	
	
	
}
