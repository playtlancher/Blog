package org.blog.blog.repos;

import org.blog.blog.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, Integer> {
    public Iterable<Post> findAll();
}
