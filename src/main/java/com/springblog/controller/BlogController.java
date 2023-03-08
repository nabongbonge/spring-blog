package com.springblog.controller;

import com.springblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
public class BlogController {

  @Autowired
  private BlogService blogService;

  @GetMapping({"","/"})
  public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = DESC)Pageable pageable) {
    model.addAttribute("articles", blogService.getArticles(pageable));
    return "index";
  }

  @GetMapping("/blog/saveForm")
  public String saveForm() {
    return "/blog/saveForm";
  }

  @GetMapping("/blog/{id}")
  public String findById(@PathVariable int id, Model model) {
    model.addAttribute("blog", blogService.getDetail(id));
    return "/blog/detail";
  }

  @GetMapping("/blog/{id}/updateForm")
  public String updateForm(@PathVariable int id, Model model) {
    model.addAttribute("blog", blogService.getDetail(id));
    return "/blog/updateForm";
  }
}
