package com.springblog.controller.api;

import com.springblog.config.auth.PrincipalDetails;
import com.springblog.domain.Blog;
import com.springblog.domain.Reply;
import com.springblog.dto.ResponseDto;
import com.springblog.service.BlogService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogApiController {

  @Autowired
  private BlogService blogService;

  @PostMapping("/api/blog")
  public ResponseDto<Integer> save(@RequestBody Blog blog, @AuthenticationPrincipal PrincipalDetails principal) {
    blogService.write(blog, principal.getUser());
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @DeleteMapping("/api/blog/{id}")
  public ResponseDto<Integer> delete(@PathVariable int id) {
    blogService.delete(id);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @PutMapping("/api/blog/{id}")
  public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Blog blog) {
    blogService.update(id, blog);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @PostMapping("/api/blog/{blogId}/reply")
  public ResponseDto<Integer> replySave(@PathVariable int blogId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetails principal) {
    blogService.replyWrite(principal.getUser(), blogId, reply);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
}
