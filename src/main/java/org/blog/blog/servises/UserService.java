package org.blog.blog.servises;

import jakarta.servlet.http.HttpSession;
import org.blog.blog.entities.User;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void registration(String username, String email, String password , Model model);
    User getUser();
    void saveAvatar(MultipartFile avatar);
}
