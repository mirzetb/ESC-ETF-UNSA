package esc.project.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.Task;

@RepositoryRestResource(collectionResourceRel = "task", path = "task")
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer>, TaskRepositoryCustom{

}
