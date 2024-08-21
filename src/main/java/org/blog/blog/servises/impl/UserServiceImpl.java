package org.blog.blog.servises.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.blog.blog.entities.User;
import org.blog.blog.servises.UserService;
import org.blog.blog.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean login(User user, HttpSession session) {
//        User userDB = userRepository.findByUsername(user.getUsername());
//        if (passwordEncoder.matches(user.getPassword(), userDB.getPassword())) {
//
//            session.setAttribute("username", userDB.getUsername());
//            session.setAttribute("id", userDB.getId());
//            return true;
//        }
        return false;
    }

    @Override
    public boolean register(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }

        return false;
    }
}
