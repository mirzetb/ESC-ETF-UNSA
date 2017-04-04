package esc.project.services;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.repositories.TaskRepository;
import esc.project.repositories.TaskRepositoryCustom;

public class TaskRepositoryImpl implements TaskRepositoryCustom{

	@Autowired
	TaskRepository taskRepo;
}
