package esc.project.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.models.Task;
import esc.project.repositories.TaskRepository;
import esc.project.services.TaskRepositoryImpl;

@RestController
public class TaskController {

	@Autowired
	TaskRepositoryImpl taskRepo;
	
	@Autowired
	TaskRepository tr;
	
	@RequestMapping(value = "/addNewTask", method=RequestMethod.POST)
	public String addNewTask(@RequestParam ("userID") Integer userID, @RequestParam("projID") Integer projID, @RequestParam("desc") String desc)
	{
		//return taskRepo.addNewTask(userID, projID, desc);
		return tr.addNewTask(userID, projID, desc);
	}
	@RequestMapping(value = "/showTasksOnProject", method=RequestMethod.GET)
	public List<Task> getAssignedTaskOnProj(@RequestParam("projID") Integer projID)
	{
		return tr.getAssignedTaskOnProj(projID);
		
	}
	
}
