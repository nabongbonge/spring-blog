package com.springblog.controller;

import com.springblog.domain.Blog;
import com.springblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

  @Autowired
  private BlogService blogService;

  @GetMapping("/saveForm")
  public String saveForm() {
    return "/blog/saveForm";
  }

  @GetMapping("/{id}")
  public String findById(@PathVariable Long id, Model model) {
    Blog detail = blogService.getDetail(id);
    model.addAttribute("blog", detail);
    return "/blog/detail";
  }

  @GetMapping("/{id}/updateForm")
  public String updateForm(@PathVariable Long id, Model model) {
    model.addAttribute("blog", blogService.getDetail(id));
    return "/blog/updateForm";
  }
}
