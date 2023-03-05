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
public class Reply {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  public int id;

  @ManyToOne
  @JoinColumn(name = "blogId")
  public Blog blog;

  @ManyToOne
  @JoinColumn(name = "userId")
  public User user;

  @Column(nullable = false, length = 200)
  public String content;

  @CreationTimestamp
  public Timestamp createDate;
}
