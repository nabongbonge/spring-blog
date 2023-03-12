package com.springblog.dto;

import com.springblog.domain.Blog;
import com.springblog.domain.User;
import com.springblog.domain.type.LoginType;
import com.springblog.domain.type.RoleType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Data
public class BlogDto {
  private Long id;
  private String title;
  private String content;
  private int count;

  public static BlogDto of(Long id, String title, String content, int count) {
    return new BlogDto(id, title, content, count);
  }

  public static BlogDto from(Blog entity) {
    return new BlogDto(entity.getId(), entity.getTitle(), entity.getContent(), entity.getCount());
  }

  public Blog toEntity() {
    return Blog.of(id, title, content, count);
  }
}
