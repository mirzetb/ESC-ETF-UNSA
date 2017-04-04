package esc.project.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.Project;

@RepositoryRestResource(collectionResourceRel = "project", path = "project")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer>, ProjectRepositoryCustom {
	
	public Project findByTitle(@Param("title") String title);
	
	
}
