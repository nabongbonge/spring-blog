package com.springblog.dto;

import com.springblog.domain.User;
import com.springblog.domain.type.LoginType;
import com.springblog.domain.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

  private Long id;
  private String username;
  private String password;
  private String email;
  private RoleType role;
  private LoginType loginType;

  public static UserDto of(Long id, String username, String password, String email,
                           RoleType role, LoginType loginType) {
    return new UserDto(id, username, password, email, role, loginType);
  }

  public static UserDto from(User entity) {
    return new UserDto(entity.getId(),
            entity.getUsername(),
            entity.getPassword(),
            entity.getEmail(),
            entity.getRole(),
            entity.getLoginType()
    );
  }

  public User toEntity(){
    return User.of(id, username, password, email, role, loginType);
  }
}

