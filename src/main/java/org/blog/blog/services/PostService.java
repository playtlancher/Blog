package org.blog.blog.services;


import org.blog.blog.entities.Comment;
import org.blog.blog.entities.Post;


public interface PostService {
    void addPost(String title, String smallDescription, String text);
    Iterable<Post> getPosts();
    Iterable<Comment> getComments(int postId);
    void addComment(int id, String comment);
}
