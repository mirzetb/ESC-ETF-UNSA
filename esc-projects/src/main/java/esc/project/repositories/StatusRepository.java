package esc.project.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.Status;

@RepositoryRestResource(collectionResourceRel = "status", path = "status")
public interface StatusRepository extends PagingAndSortingRepository<Status, Integer>, StatusRepositoryCustom{

	
	public Status findByCode(@Param("code") String code);
}
