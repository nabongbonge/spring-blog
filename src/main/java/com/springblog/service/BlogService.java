package com.springblog.service;

import com.springblog.domain.Blog;
import com.springblog.domain.Reply;
import com.springblog.domain.User;
import com.springblog.dto.BlogDto;
import com.springblog.dto.ReplyDto;
import com.springblog.execption.NotFoundBlogException;
import com.springblog.execption.NotFoundUserException;
import com.springblog.repository.BlogRepository;
import com.springblog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {

  @Autowired
  private BlogRepository blogRepository;

  @Autowired
  private ReplyRepository replyRepository;

  @Autowired
  private UserService userService;

  @Transactional
  public void write(BlogDto blogDto, String userName) {

    User user = userService.findUser(userName);
    Blog blog = blogDto.toEntity();
    blog.setUser(user);

    blogRepository.save(blog);
  }

  @Transactional(readOnly = true)
  public Page<Blog> getArticles(Pageable pageable) {
    return blogRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Blog getDetail(Long id) {
    return blogRepository.findById(id)
            .orElseThrow(NotFoundUserException::new);
  }

  @Transactional
  public void delete(Long id) {
    blogRepository.deleteById(id);
  }

  @Transactional
  public void update(Long id, BlogDto blogDto) {
    Blog findBlog = blogRepository.findById(id).orElseThrow(NotFoundBlogException::new);
    findBlog.changeTitle(blogDto.getTitle());
    findBlog.changeContent(blogDto.getContent());
  }

  @Transactional
  public void replyWrite(ReplyDto replyDto) {
    User user = userService.findByUserId(replyDto.getUserId());

    Blog blog = blogRepository.findById(replyDto.getBlogId())
            .orElseThrow(NotFoundBlogException::new);

    Reply reply = Reply.of(null, blog, user, replyDto.getContent());

    replyRepository.save(reply);
  }

  public void replyDelete(Long replyId) {
    replyRepository.deleteById(replyId);
  }
}
