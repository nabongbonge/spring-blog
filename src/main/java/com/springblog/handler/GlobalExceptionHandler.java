package com.springblog.handler;

import com.springblog.dto.ResponseDto;
import com.springblog.execption.BlogException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler(value = BlogException.class)
  public ResponseEntity<ResponseDto<String>> handleArgumentException(BlogException e) {
    return ResponseEntity.badRequest().body(ResponseDto.ofFail(e.getCode(), e.getMsg()));
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseDto<String> handleRunTimeException(Exception e) {
    return ResponseDto.ofFail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }
}
