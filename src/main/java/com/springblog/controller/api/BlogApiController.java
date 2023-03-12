package com.springblog.controller.api;

import com.springblog.config.auth.PrincipalDetails;
import com.springblog.dto.BlogDto;
import com.springblog.dto.ReplyDto;
import com.springblog.dto.ResponseDto;
import com.springblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/blog")
@RestController
public class BlogApiController {

  private final BlogService blogService;

  @PostMapping("/")
  public ResponseDto<Integer> insert(@RequestBody BlogDto blogDto, @AuthenticationPrincipal PrincipalDetails principal) {
    blogService.write(blogDto, principal.getUser().getUsername());
    return ResponseDto.ofSuccess(1);
  }

  @DeleteMapping("/{id}")
  public ResponseDto<Integer> delete(@PathVariable Long id) {
    blogService.delete(id);
    return ResponseDto.ofSuccess(1);
  }

  @PutMapping("/{id}")
  public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody BlogDto blogDto) {
    blogService.update(id, blogDto);
    return ResponseDto.ofSuccess(1);
  }

  @PostMapping("/{blogId}/reply")
  public ResponseDto<Integer> replySave(@RequestBody ReplyDto replyDto) {
    blogService.replyWrite(replyDto);
    return ResponseDto.ofSuccess(1);
  }

  @DeleteMapping("/{blogId}/reply/{replyId}")
  public ResponseDto<Integer> replyDelete(@PathVariable Long replyId) {
    blogService.replyDelete(replyId);
    return ResponseDto.ofSuccess(1);
  }
}
