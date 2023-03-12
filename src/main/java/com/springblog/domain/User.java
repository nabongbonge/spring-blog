package com.springblog.domain;

import com.springblog.domain.type.LoginType;
import com.springblog.domain.type.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Entity
public class User extends AuditingFields {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "userId")
  private Long id;

  @Column(unique = true, nullable = false, length = 100)
  private String username;

  @Column(nullable = false, length = 100)
  private String password;

  @Column(nullable = false, length = 50)
  private String email;

  @Enumerated(EnumType.STRING)
  private RoleType role;

  private LoginType loginType;

  public void changePassword(String password) {
    this.password = password;
  }

  public void changeEmail(String email) {
    this.email = email;
  }

  public static User of(Long id, String username, String password, String email, RoleType role, LoginType loginType) {
    return new User(id, username, password, email, role, loginType);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", role=" + role +
            ", loginType=" + loginType +
            '}';
  }
}
