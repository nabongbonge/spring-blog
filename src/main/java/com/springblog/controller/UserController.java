package com.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  // 회원 가입 화면
  @GetMapping("/auth/joinForm")
  public String joinForm() {
    return "user/joinForm";
  }

  // 로그인 화면
  @GetMapping("/auth/loginForm")
  public String loginForm() {
    return "user/loginForm";
  }

  @GetMapping("/user/updateForm")
  public String updateForm() {
    return "user/updateForm";
  }
}
