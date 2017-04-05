package esc.project.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.models.Task;
import esc.project.models.TeamMember;
import esc.project.repositories.ProjectRepository;
import esc.project.repositories.TaskRepository;
import esc.project.repositories.TaskRepositoryCustom;
import esc.project.repositories.TeamMemberRepository;

public class TaskRepositoryImpl implements TaskRepositoryCustom{

	@Autowired
	TaskRepository taskRepo;

	@Autowired 
	ProjectRepository projectRepo;
	
	@Autowired
	TeamMemberRepository teamMemRepo;
	
	
	@Override
	public String addNewTask(int userID, int projID, String desc) {
		
		TeamMember tm = teamMemRepo.findTMByProjID(projID, userID);
		
		if(tm != null)
		{
			Task t = new Task(desc, new Timestamp(System.currentTimeMillis()), tm);
			taskRepo.save(t);
			return "Uspješno ste dodali novi zadatak članu: " + tm.getUser().getFirstName() + " " + tm.getUser().getLastName() + ".";
		}
		return "Nepostojeći projekat ili član tima.";
	}




}
