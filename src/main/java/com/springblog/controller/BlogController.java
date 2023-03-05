package com.springblog.controller;

import com.springblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

  @Autowired
  private BlogService blogService;

  @GetMapping({"","/"})
  public String index(Model model) {
    model.addAttribute("articles", blogService.getArticles());
    return "index";
  }

  @GetMapping("/blog/saveForm")
  public String saveForm() {
    return "/blog/saveForm";
  }
}
