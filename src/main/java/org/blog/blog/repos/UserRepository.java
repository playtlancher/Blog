package org.blog.blog.repos;

import org.springframework.data.repository.CrudRepository;
import org.blog.blog.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
