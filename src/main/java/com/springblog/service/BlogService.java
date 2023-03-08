package com.springblog.service;

import com.springblog.domain.Blog;
import com.springblog.domain.Reply;
import com.springblog.domain.User;
import com.springblog.dto.ReplySaveRequestDto;
import com.springblog.repository.BlogRepository;
import com.springblog.repository.ReplyRepository;
import com.springblog.repository.UserRepository;
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
  private UserRepository userRepository;

  @Transactional
  public void write(Blog blog, User user) {
    blog.setUser(user);
    blogRepository.save(blog);
  }

  @Transactional(readOnly = true)
  public Page<Blog> getArticles(Pageable pageable) {
    return blogRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Blog getDetail(int id) {
    return blogRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다."));
  }

  @Transactional
  public void delete(int id) {
    blogRepository.deleteById(id);
  }

  @Transactional
  public void update(int id, Blog requestBlog) {
    Blog findBlog = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다."));
    findBlog.setTitle(requestBlog.getTitle());
    findBlog.setContent(requestBlog.getContent());
  }

  @Transactional
  public void replyWrite(ReplySaveRequestDto replySaveRequestDto) {
    User user = userRepository.findById(replySaveRequestDto.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 사용자 ID를 찾을 수 없습니다."));

    Blog blog = blogRepository.findById(replySaveRequestDto.getBlogId())
            .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 게시글 ID를 찾을 수 없습니다."));

    Reply reply = Reply.builder()
            .user(user)
            .blog(blog)
            .content(replySaveRequestDto.getContent())
            .build();

    replyRepository.save(reply);
  }

  @Transactional
  public void replyWriteForNativeQuery(ReplySaveRequestDto replySaveRequestDto) {
    replyRepository.saveForNateiveQuery(replySaveRequestDto.getUserId()
            , replySaveRequestDto.getBlogId(), replySaveRequestDto.getContent());
  }
}
