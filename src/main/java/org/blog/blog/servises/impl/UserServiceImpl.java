package org.blog.blog.servises.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.blog.blog.entities.User;
import org.blog.blog.servises.UserService;
import org.blog.blog.repos.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registration(User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        model.addAttribute("user", saved);
    }

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        User user = userRepository.findByUsername(username).get();
        return user;
    }

    @Override
    public void saveAvatar(MultipartFile avatar) {
        User user = getUser();
        user.setAvatar(transferFile(avatar));
        userRepository.save(user);
    }

    public String transferFile(MultipartFile file) {
        String pathToFolder = System.getProperty("user.home") + File.separator + "images" + File.separator;
        String id = UUID.randomUUID().toString();
        try {
            file.transferTo(new File(pathToFolder + id + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id + ".jpg";
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
