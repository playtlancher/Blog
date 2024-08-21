package org.blog.blog.services.impl;

import org.blog.blog.entities.Comment;
import org.blog.blog.entities.Post;
import org.blog.blog.entities.User;
import org.blog.blog.repos.CommentRepository;
import org.blog.blog.repos.PostRepository;
import org.blog.blog.repos.UserRepository;
import org.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void addPost(String title, String smallDescription, String text) {
        Post post = new Post(title, smallDescription, text, getUser());
        postRepository.save(post);
    }

    @Override
    public Iterable<Post> getPosts() {
        Iterable<Post> posts = postRepository.findAll();
        return posts;
    }

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
    public void addComment(int id, String commentText) {
        User user = getUser();
        Post post = postRepository.findById(id).get();
        Comment comment = new Comment(commentText, user, post);
    }

    @Override
    public Iterable<Comment> getComments(int postId) {
        return commentRepository.findByPostId(postId);
    }
}
