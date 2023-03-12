package com.springblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
  private Long id;
  private Long userId;
  private Long blogId;
  private String content;
}
