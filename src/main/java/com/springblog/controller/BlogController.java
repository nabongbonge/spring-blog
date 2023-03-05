package com.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

  @GetMapping({"","/"})
  public String index() {
    return "index";
  }

  @GetMapping("/blog/saveForm")
  public String saveForm() {
    return "/blog/saveForm";
  }
}
