package com.niit.blogapi.service;

import com.niit.blogapi.domain.BlogPost;
import com.niit.blogapi.domain.User;

import java.util.List;

public interface BlogService {
    List<BlogPost> getAllBlogPosts();
    List<BlogPost> getAllBlogPostsByUser(User user);
    List<BlogPost> searchBlogPosts(String keyword);
    void commentOnBlogPost(Long postId, String comment);

    BlogPost createBlogPost(BlogPost blogPost);
}
