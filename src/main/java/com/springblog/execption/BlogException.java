package com.springblog.execption;

import lombok.Getter;

@Getter
public class BlogException extends RuntimeException{
  private int code;
  private String msg;

  public BlogException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }
}
