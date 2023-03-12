package com.springblog.execption;

public class NotFoundUserException extends BlogException {

  public NotFoundUserException() {
    super(1111, "사용자 없음");
  }
}
