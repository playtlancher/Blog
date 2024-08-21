package org.blog.blog.servises;

import jakarta.servlet.http.HttpSession;
import org.blog.blog.entities.User;

public interface UserService {
    boolean login(User user, HttpSession session);

    boolean register(User user);
}
