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

  public Blog getDetail(int id) {
    return blogRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));
  }
}
