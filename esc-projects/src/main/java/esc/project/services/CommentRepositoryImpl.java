package esc.project.services;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.repositories.CommentRepository;
import esc.project.repositories.CommentRepositoryCustom;

public class CommentRepositoryImpl implements CommentRepositoryCustom{

	@Autowired
	CommentRepository commentRepo;
	
}
