package org.blog.blog.Controller;


import org.blog.blog.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/user/home")
    public String loginUser(Model model) {
        return "home";
    }


    @GetMapping("/logout")
    public String handleLogout(Model model) {
        return "login";
    }


}
