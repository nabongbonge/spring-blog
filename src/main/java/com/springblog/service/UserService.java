package com.springblog.service;


import com.springblog.domain.RoleType;
import com.springblog.domain.User;
import com.springblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Transactional
  public int join(User user) {
    try {
      user.setPassword(encoder.encode(user.getPassword()));
      user.setRole(RoleType.USER);
      userRepository.save(user);
      return 1;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("UserService:회원가입() : " + e.getMessage());
    }
    return -1;
  }

  @Transactional
  public void update(User user) {
    User findUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패"));

    if (findUser.getOauth() == null || findUser.getOauth().equals("")) {
      findUser.setPassword(encoder.encode(user.getPassword()));
      findUser.setEmail(user.getEmail());
    }

    // 세션 변경
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  @Transactional(readOnly = true)
  public User findUser(String username) {
    return userRepository.findByUsername(username).orElseGet(User::new);
  }
}
