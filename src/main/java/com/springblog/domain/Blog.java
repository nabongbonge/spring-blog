package com.springblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
  @Column(name = "blogId")
  private int id;

  @Column(nullable = false, length = 100)
  private String title;

  @Lob
  private String content;

  @ColumnDefault("0")
  private int count;

  @ManyToOne(fetch = EAGER)
  @JoinColumn(name = "userId")
  private User user;

  @OneToMany(mappedBy = "blog", fetch = EAGER)
  @JsonIgnoreProperties({"blog", "user"})
  @OrderBy("id desc")
  private List<Reply> replies;

  @CreationTimestamp
  private Timestamp createDate;
}
