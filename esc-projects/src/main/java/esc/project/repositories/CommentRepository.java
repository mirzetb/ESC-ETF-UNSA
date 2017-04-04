package esc.project.repositories;



import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import esc.project.models.Comment;

@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>, CommentRepositoryCustom {

}
