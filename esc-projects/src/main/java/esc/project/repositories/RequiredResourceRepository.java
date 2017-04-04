package esc.project.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.RequiredResource;

@RepositoryRestResource(collectionResourceRel = "reqResource", path = "reqResource")
public interface RequiredResourceRepository extends PagingAndSortingRepository<RequiredResource, Integer> , RequiredResourceRepositoryCustom {

}
