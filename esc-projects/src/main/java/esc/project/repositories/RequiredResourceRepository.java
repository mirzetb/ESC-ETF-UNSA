package esc.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.Project;
import esc.project.models.RequiredResource;

@RepositoryRestResource(collectionResourceRel = "reqResource", path = "reqResource")
public interface RequiredResourceRepository extends PagingAndSortingRepository<RequiredResource, Integer> , RequiredResourceRepositoryCustom {

	
	@Query("SELECT rr FROM RequiredResource rr WHERE rr.project = :proj")
	public List<RequiredResource> getRRsOnProject(@Param("proj") Project proj);
}
