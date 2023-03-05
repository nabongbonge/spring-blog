package com.springblog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Blog {

  @Id
  @GeneratedValue(strategy =  IDENTITY)
  private int id;

  @Column(nullable = false, length = 100)
  private String title;

  @Lob
  private String content;

  @Column(columnDefinition = "0")
  private int count;

  @ManyToOne(fetch = EAGER)
  @JoinColumn(name = "userId")
  private User user;

  @OneToMany(mappedBy = "blog", fetch = EAGER)
  private List<Reply> replis;

  @CreationTimestamp
  private Timestamp createDate;
}
