package esc.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.Task;
import esc.project.models.TeamMember;

@RepositoryRestResource(collectionResourceRel = "task", path = "task")
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer>, TaskRepositoryCustom{

	@Query	("SELECT t FROM Task t INNER JOIN t.teamMemeber ttm inner join ttm.project p where p.id = :projID ") 
	public List<Task> getAssignedTaskOnProj(@Param ("projID") Integer projID);
}
