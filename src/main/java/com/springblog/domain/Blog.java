package com.springblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Blog extends AuditingFields {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "blogId")
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Lob
  private String content;

  @ColumnDefault("0")
  private int count;

  @Setter
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "userId")
  private User user;

  @OneToMany(mappedBy = "blog", fetch = LAZY)
  @JsonIgnoreProperties({"blog", "user"})
  @OrderBy("id desc")
  private List<Reply> replies;

  public void changeTitle(String title) {
    this.title = title;
  }

  public void changeContent(String content) {
    this.content = content;
  }

  private Blog(Long id, String title, String content, int count) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.count = count;
  }

  public static Blog of(Long id, String title, String content, int count) {
    return new Blog(id, title, content, count);
  }

  @Override
  public String toString() {
    return "Blog{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", count=" + count +
            ", user=" + user +
            '}';
  }
}
