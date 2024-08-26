package org.blog.blog.Controller;

import org.blog.blog.repos.CommentRepository;
import org.blog.blog.repos.PostRepository;
import org.blog.blog.repos.UserRepository;
import org.blog.blog.servises.PostService;
import org.blog.blog.servises.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class PostController {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private PostService postService;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository, PostServiceImpl postServiceImpl, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postService = postServiceImpl;
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "blog";
    }
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("comments", postService.getComments(id));
        model.addAttribute("post", postRepository.findById(id).get());
        return "details";
    }

    @GetMapping("/blog/add")
    public String addPost(Model model) {
        return "add-post";
    }

    @PostMapping("/blog/add")
    public String handlePost(@RequestParam String title, @RequestParam String smallDescription, @RequestParam String text, Model model) {
        postService.addPost(title, smallDescription, text);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/leave-comment")
    public String leaveComment(@RequestParam String comment , @PathVariable("id") int id, Model model) {
        postService.addComment(id, comment);
        return "redirect:/blog/" + id;
    }
}
