package com.springblog.execption;

public class NotFoundBlogException extends BlogException{

  public NotFoundBlogException() {
    super(2222,"게시글 없음.");
  }
}
