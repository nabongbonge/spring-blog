package com.springblog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "userId")
  private int id;

  @Column(unique = true, nullable = false, length = 30)
  private String username;

  @Column(nullable = false, length = 100)
  private String password;

  @Column(nullable = false, length = 50)
  private String email;

  @Enumerated(EnumType.STRING)
  private RoleType role;

  @CreationTimestamp
  private Timestamp createDate;
}
