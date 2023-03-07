package com.springblog.controller.api;

import com.springblog.domain.User;
import com.springblog.dto.ResponseDto;
import com.springblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

  @Autowired
  private UserService userService;

  @PostMapping("/auth/joinProc")
  public ResponseDto<Integer> save(@RequestBody User user) {
    System.out.println("UserApiController : save 호출됨.");
    int reuslt = userService.join(user);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), reuslt);
  }

  @PutMapping("/user")
  public ResponseDto<Integer> update(@RequestBody User user) {
    userService.update(user);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
}