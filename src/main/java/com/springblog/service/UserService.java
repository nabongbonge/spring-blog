package com.springblog.service;


import com.springblog.domain.type.RoleType;
import com.springblog.domain.User;
import com.springblog.dto.UserDto;
import com.springblog.execption.NotFoundUserException;
import com.springblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Transactional
  public User join(UserDto userDto) {
    try {
      userDto.setPassword(encoder.encode(userDto.getPassword()));
      userDto.setRole(RoleType.USER);
      User user = userDto.toEntity();
      return userRepository.save(user);
    } catch (Exception e) {
      e.printStackTrace();
      log.error("UserService:회원가입() : ", e.getMessage());
    }
    return null;
  }

  @Transactional
  public void update(UserDto userDto) {
    User findUser = userRepository.findById(userDto.getId())
            .orElseThrow(NotFoundUserException::new);

    if (findUser.getLoginType() == null || findUser.getLoginType().equals("")) {
      findUser.changePassword(encoder.encode(userDto.getPassword()));
      findUser.changeEmail(userDto.getEmail());
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

  }

  @Transactional(readOnly = true)
  public User findUser(String username) {
    return userRepository.findByUsername(username).orElseThrow(NotFoundUserException::new);
  }

  @Transactional(readOnly = true)
  public User findByUserId(Long userId) {
    return userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
  }
}
