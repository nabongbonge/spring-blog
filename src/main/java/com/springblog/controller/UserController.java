package com.springblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springblog.config.auth.PrincipalDetailsService;
import com.springblog.domain.User;
import com.springblog.domain.type.LoginType;
import com.springblog.domain.type.RoleType;
import com.springblog.dto.KakaoProfile;
import com.springblog.dto.OAuthTokenDto;
import com.springblog.dto.UserDto;
import com.springblog.execption.NotFoundUserException;
import com.springblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class UserController {

  @Autowired
  private PrincipalDetailsService principalDetailsService;

  @Autowired
  BCryptPasswordEncoder encoder;

  @Autowired
  private UserService userService;

  @Value("${oauth.password}")
  public String oauthPassword;


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

  @GetMapping("/auth/kakao/callback")
  public String kakaoCallback(String code, HttpSession session) {

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

    ObjectMapper om = new ObjectMapper();
    OAuthTokenDto oAuthTokenDto = null;

    try {
      oAuthTokenDto = om.readValue(response.getBody(), OAuthTokenDto.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    headers.clear();
    headers.add("Authorization", "Bearer ".concat(oAuthTokenDto.getAccess_token()));
    headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(headers);

    ResponseEntity<String> profileResponse = rt.exchange("https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET,
            profileRequest,
            String.class
    );

    KakaoProfile kakaoProfile = null;
    try {
      kakaoProfile = om.readValue(profileResponse.getBody(), KakaoProfile.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }


    UserDto userDto = UserDto.of(null, kakaoProfile.getKakaoAccount().getEmail() + "_" + kakaoProfile.getId(),
            oauthPassword, kakaoProfile.getKakaoAccount().getEmail(), RoleType.USER, LoginType.KAKAO);


    try {
      User findUSer = userService.findUser(userDto.getUsername());
    } catch (NotFoundUserException e) {
      log.info("자동 로그인을 진행합니다.");
      userService.join(userDto);
    }

    UserDetails userDetails = principalDetailsService.loadUserByUsername(userDto.getUsername());
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authentication);
    session.setAttribute("SPRING_SECURITY_CONTEXT",securityContext);

    // 세션 변경
//    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), encoder.encode(userDto.getPassword())));
//    SecurityContextHolder.getContext().setAuthentication(authentication);
    // Resolved [org.springframework.security.authentication.BadCredentialsException: 자격 증명에 실패하였습니다.]

    return "redirect:/";
  }
}
