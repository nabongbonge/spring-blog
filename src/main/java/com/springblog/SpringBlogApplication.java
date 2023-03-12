package com.springblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class SpringBlogApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBlogApplication.class, args);
  }

}
