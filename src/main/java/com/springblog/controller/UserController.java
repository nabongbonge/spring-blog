package com.springblog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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

  @ResponseBody
  @GetMapping("/auth/kakao/callback")
  public String kakaoCallback(String code) {

    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", "560dcdfcd360106efdc8710475eb840b");
    params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
    params.add("code", code);

    HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<>(params, headers);

    ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com//oauth/token",
            HttpMethod.POST,
            kakaoTokenReq,
            String.class
    );

    return "카카오 토큰 요청 완료 : 토큰 요청에 대한 응답 : " + response.getBody();
  }
}
