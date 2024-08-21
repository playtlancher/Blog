package org.blog.blog.repos;

import org.blog.blog.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findByPostId(int postId);
}
