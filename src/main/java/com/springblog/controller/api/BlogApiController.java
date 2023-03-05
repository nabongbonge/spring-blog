package com.springblog.controller.api;

import com.springblog.config.auth.PrincipalDetails;
import com.springblog.domain.Blog;
import com.springblog.dto.ResponseDto;
import com.springblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogApiController {

  @Autowired
  private BlogService blogService;

  @PostMapping("/api/board")
  public ResponseDto<Integer> save(@RequestBody Blog blog, @AuthenticationPrincipal PrincipalDetails principal) {
    blogService.write(blog, principal.getUser());
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
}
