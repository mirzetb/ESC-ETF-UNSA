package com.project.nwt.controller;



import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.project.nwt.repository.DepartmentRepository;
import com.project.nwt.repository.UserRepository;
import com.project.nwt.IRepository.IDepartmentRepository;
import com.project.nwt.model.Department;
import com.project.nwt.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@EnableAutoConfiguration
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	private UserRepository _userRepository;
	
	@Autowired
	private IDepartmentRepository depRep;
	
	@Autowired
	private JavaMailSender mailSender;
	
		
	public UserController(UserRepository userRepository)
	{
		if(userRepository != null)
			_userRepository = userRepository;
		
		
	}
	

	
	@RequestMapping(value = "/getByUsername", method=RequestMethod.GET)
	public User getByUsername(@RequestParam(value = "username") String user)
	{
		return _userRepository.findByUsername(user);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAll(){
		return _userRepository.getAll();
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public User getById(@RequestParam(value = "id", required = true) String id){
		return _userRepository.getById(Integer.parseInt(id));
	}
	
	
	@RequestMapping(value = "/save",  method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody User u){
		log.info(u.getUniqueID());
		if(u != null){
			_userRepository.save(u);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean delete(@RequestParam(value = "id", required = true) int id){
		return _userRepository.delete(id);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<User> search(@RequestParam(value = "firstname", required = false) String firstname, @RequestParam(value = "lastname", required = false) String lastname){
		return _userRepository.search(firstname, lastname);
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void AddUser(HttpServletRequest req, @RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname, @RequestParam("escID") String escid, @RequestParam("username") String username, @RequestParam("password") String pass, @RequestParam("email") String email, @RequestParam("phoneNumber") String phone_number, @RequestParam("role") String role, @RequestParam("department") String department)
	{
		Cookie[] cookies = req.getCookies();
		String cookieString = "";
		if(cookies != null)
		{
			for(Cookie cook : cookies)
			{
				if(cook.getName().equals("Auth"))
				{
					cookieString = cook.getValue();
					break;
				}
					
			}
		}
		_userRepository.addNewUser(cookieString, firstname, lastname, escid, username, pass, email, phone_number, role, department, 0, 0);
		
	}
	
	@RequestMapping(value="/sendValidationMail", method = RequestMethod.GET)
	public void sendEmail(@RequestParam("mailTo") String recipient, @RequestParam("uid") String uniqueId)
	{		
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(recipient);
		message.setFrom("esc.etf.sarajevo@gmail.com");
		message.setSubject("Verifikacija Embedded System Club računa");
		String body = "Kako bi postali verifikovani član Embedded System kluba, kliknite na link: http://localhost:8070/verifyUserMail?uid="+uniqueId;
		message.setText(body);
		mailSender.send(message);		
						
		
	}
	
	@RequestMapping(value ="/verifyUserMail", method = RequestMethod.GET)		
	public void verifyMail(@RequestParam("uid") String uid, HttpServletResponse res) throws IOException
	{
		User u = _userRepository.findByUniqueID(uid);
		if(u != null)
		{
			u.setValidated(1);
			_userRepository.save(u);
			 res.sendRedirect("http://localhost:8070/profile");
		}
		
	}
	
	@RequestMapping(value="/changePassword", method = RequestMethod.POST)
	public String changeUserPass(@RequestParam("username") String username, @RequestParam("newPass") String newPass,  HttpServletResponse res) throws IOException
	{
		User u = _userRepository.findByUsername(username);
		if(u != null)
		{
			u.setPassword(newPass);
			_userRepository.save(u);
			
			return "Uspješno promijenjena lozinka";
			
		}
		return "Nešto je pošlo po zlu";
	}
	
	@RequestMapping(value="/logoutUser", method=RequestMethod.GET)
	public String logoutMethod(HttpServletRequest req, HttpServletResponse resp) 
	{
		Cookie[] cookies = req.getCookies();
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals("Auth"))
				{
					c.setValue(null);
					c.setPath("/");
					c.setMaxAge(0);
					resp.addCookie(c);
					break;
				}
			}
		}
		
		
		return "Token removed from cookie, safe to logout";
	}
	
	
	
}
