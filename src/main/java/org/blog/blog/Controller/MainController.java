package org.blog.blog.Controller;


import org.blog.blog.repos.UserRepository;
import org.blog.blog.servises.UserService;
import org.blog.blog.servises.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MainController {

    private final UserService userService;
    @Autowired
    public MainController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/logout")
    public String handleLogout(Model model) {
        return "login";
    }
    @GetMapping("/")

    public String home(Model model) {
        return "login";
    }

    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("user", userService.getUser());
        return "account";
    }

    @PostMapping("/account/change-avatar")
    public String changeAvatar(Model model , @RequestParam MultipartFile avatar) {
        userService.saveAvatar(avatar);
        return "redirect:/account";
    }
}
