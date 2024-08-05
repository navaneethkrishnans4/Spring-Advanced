package com.niit.blogapi.service;

import com.niit.blogapi.domain.BlogPost;
import com.niit.blogapi.domain.User;
import com.niit.blogapi.repository.BlogPostRepository;
import com.niit.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    @Override
    public List<BlogPost> getAllBlogPostsByUser(User user) {
        return user.getBlogPosts();
    }

    @Override
    public List<BlogPost> searchBlogPosts(String keyword) {
        // Implement search logic here
        return null;
    }

    @Override
    public void commentOnBlogPost(Long postId, String comment) {
        // Implement comment logic here
    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }
}
