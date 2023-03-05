package com.springblog.service;

import com.springblog.domain.Blog;
import com.springblog.domain.User;
import com.springblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {

  @Autowired
  private BlogRepository blogRepository;

  @Transactional
  public void write(Blog blog, User user) {
    blog.setUser(user);
    blogRepository.save(blog);
  }

}
