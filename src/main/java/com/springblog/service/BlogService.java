package com.springblog.service;

import com.springblog.domain.Blog;
import com.springblog.domain.User;
import com.springblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {

  @Autowired
  private BlogRepository blogRepository;

  @Transactional
  public void write(Blog blog, User user) {
    blog.setUser(user);
    blogRepository.save(blog);
  }

  public Page<Blog> getArticles(Pageable pageable) {
    return blogRepository.findAll(pageable);
  }
}
