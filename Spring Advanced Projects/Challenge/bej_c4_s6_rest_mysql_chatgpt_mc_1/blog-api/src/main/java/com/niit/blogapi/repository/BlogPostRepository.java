package com.niit.blogapi.repository;

import com.niit.blogapi.domain.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
}
