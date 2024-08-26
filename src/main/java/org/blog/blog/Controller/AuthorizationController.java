package org.blog.blog.Controller;


import org.blog.blog.entities.User;
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
    public String handleRegistration(Model model,@RequestParam String confirmPassword , User user) {
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            System.out.println("error 1");
            return "registration";
        }
        if (userService.isUsernameTaken(user.getUsername())) {
            model.addAttribute("error", "Username is already taken.");
            System.out.println("error 2");
            return "registration";
        }

        if (userService.isEmailTaken(user.getEmail())) {
            model.addAttribute("error", "Email is already taken.");
            System.out.println("error 3");
            return "registration";
        }
        userService.registration(user, model);
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
