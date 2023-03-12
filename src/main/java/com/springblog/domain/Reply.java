package com.springblog.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Reply extends AuditingFields{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "replyId")
  public Long id;

  @ManyToOne
  @JoinColumn(name = "blogId")
  public Blog blog;

  @ManyToOne
  @JoinColumn(name = "userId")
  public User user;

  @Column(nullable = false, length = 200)
  public String content;


  public static Reply of (Long id, Blog blog, User user, String content) {
    return new Reply(id, blog, user, content);
  }
}
