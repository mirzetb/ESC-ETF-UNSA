package esc.project.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.models.Comment;
import esc.project.models.Project;
import esc.project.models.UsersMeta;
import esc.project.repositories.CommentRepository;
import esc.project.repositories.CommentRepositoryCustom;
import esc.project.repositories.ProjectRepository;
import esc.project.repositories.UsersMetaRepository;

public class CommentRepositoryImpl implements CommentRepositoryCustom{

	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	UsersMetaRepository userRepo;

	@Override
	public String addNewComment(int projID, int authorID, String content) {
		
		UsersMeta user = userRepo.findOne(authorID);
		Project proj = projectRepo.findOne(projID);
		if(proj != null && user != null)
		{
			Comment c = new Comment(content, new Timestamp(System.currentTimeMillis()), proj, user);
			commentRepo.save(c);
			return "Novi komentar uspješno dodan! Projekat: " + proj.getTitle() + ", Autor: " + user.getFirstName() + " " + user.getLastName() +".";
		}
		return "Nepostojeći autor ili projekat!";
		
		
	}
	
}
