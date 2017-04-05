package esc.project.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.Comment;

@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>, CommentRepositoryCustom {

	@Query("SELECT c FROM Comment c WHERE c.project.id = :projID")
	public List<Comment> getAllCommsOnProj(@Param ("projID") Integer projID);
	
	@Query("SELECT c FROM Comment c WHERE c.user.id = :userID")
	public List<Comment> getAllCommsOfAuthor(@Param("userID") Integer userID);
}
