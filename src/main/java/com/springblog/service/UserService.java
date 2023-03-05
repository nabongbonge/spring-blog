package com.springblog.service;


import com.springblog.domain.RoleType;
import com.springblog.domain.User;
import com.springblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

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
}