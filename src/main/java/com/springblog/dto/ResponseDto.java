package com.springblog.dto;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
  private int status;
  private T data;

  public static <T> ResponseDto<T> ofSuccess(T data) {
    return new ResponseDto<>(200, data);
  }

  public static <T> ResponseDto<T> ofFail(int status, T data) {
    return new ResponseDto<>(status, data);
  }
}
