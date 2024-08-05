package com.niit.blogapi.controller;

import com.niit.blogapi.domain.BlogPost;
import com.niit.blogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogService.getAllBlogPosts();
    }

    @GetMapping("/{userId}")
    public List<BlogPost> getAllBlogPostsByUser(@PathVariable Long userId) {
        // Implement logic to fetch user by ID and return their blog posts
        return null;
    }

    @GetMapping("/search")
    public List<BlogPost> searchBlogPosts(@RequestParam String keyword) {
        return blogService.searchBlogPosts(keyword);
    }

    @PostMapping("/{postId}/comment")
    public void commentOnBlogPost(@PathVariable Long postId, @RequestBody String comment) {
        blogService.commentOnBlogPost(postId, comment);
    }

    @PostMapping("/create")
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        return blogService.createBlogPost(blogPost);
    }
}