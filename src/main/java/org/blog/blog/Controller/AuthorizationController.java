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

@Controller
public class AuthorizationController {

    private final UserService userService;

    @Autowired
    public AuthorizationController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String handleRegistration(Model model,
                                     @RequestParam String username,
                                     @RequestParam String password,
                                     @RequestParam String email) {
        userService.registration(username, email, password, model);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String handleLogin(Model model) {
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
