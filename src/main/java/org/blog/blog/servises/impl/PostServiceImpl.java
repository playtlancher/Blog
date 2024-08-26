package org.blog.blog.servises.impl;

import org.blog.blog.entities.Comment;
import org.blog.blog.entities.Post;
import org.blog.blog.entities.User;
import org.blog.blog.repos.CommentRepository;
import org.blog.blog.repos.PostRepository;
import org.blog.blog.repos.UserRepository;
import org.blog.blog.servises.PostService;
import org.blog.blog.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public void addPost(String title, String smallDescription, String text) {
        Post post = new Post(title, smallDescription, text, userService.getUser());
        postRepository.save(post);
    }

    @Override
    public Iterable<Post> getPosts() {
        Iterable<Post> posts = postRepository.findAll();
        return posts;
    }



    @Override
    public void addComment(int id, String commentText) {
        User user = userService.getUser();
        Post post = postRepository.findById(id).get();
        Comment comment = new Comment(commentText, user, post);
        commentRepository.save(comment);
    }

    @Override
    public Iterable<Comment> getComments(int postId) {
        return commentRepository.findByPostId(postId);
    }
}
