package esc.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.models.Comment;
import esc.project.repositories.CommentRepository;
import esc.project.services.CommentRepositoryImpl;

@RestController
public class CommentController {

	@Autowired
	CommentRepositoryImpl commentRepo;
	@Autowired
	CommentRepository commRep;
	
	
	
	@RequestMapping(value="/commentsOnProject", method=RequestMethod.GET)
	public List<Comment> getAllCommentsOnProject(@RequestParam("projID") Integer projID)
	{
		return commRep.getAllCommsOnProj(projID);
	}
	
	@RequestMapping(value="/commentsOfAuthor", method=RequestMethod.GET)
	public List<Comment> getAllCommentsOfAuthor(@RequestParam("userID") Integer userID)
	{
		return commRep.getAllCommsOfAuthor(userID);
	}
	
	@RequestMapping(value="/addNewComment", method=RequestMethod.POST)
	public String addNewComm(@RequestParam("projID") Integer projID, @RequestParam("userID") Integer userID, @RequestParam("content") String content)
	{
		return commentRepo.addNewComment(projID, userID, content);
	}
}
