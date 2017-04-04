package esc.project.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.UsersMeta;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UsersMetaRepository extends PagingAndSortingRepository<UsersMeta, Integer>, UsersMetaRepositoryCustom {

}
