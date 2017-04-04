package esc.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.TeamMember;

@RepositoryRestResource(collectionResourceRel = "teamMember", path = "teamMember")
public interface TeamMemberRepository extends PagingAndSortingRepository<TeamMember, Integer>, TeamMemberRepositoryCustom {

	
@Query ("Select count(tm.id) from TeamMember tm where tm.project.id = :projID")
int getNoOfTmsOnProj(@Param ("projID") Integer projID);

@Query ("Select tm from TeamMember tm where tm.project.id = :projID")
List<TeamMember> getTMsOnProj(@Param("projID") Integer projID);
	
	
	
}
