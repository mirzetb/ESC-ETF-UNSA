package esc.project.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.Activity;


@RepositoryRestResource(collectionResourceRel = "activity", path = "activity")
public interface ActivityRepository extends PagingAndSortingRepository<Activity, Integer>, ActivityRepositoryCustom{

}
