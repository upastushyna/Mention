package com.mention.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class MyErrorController implements ErrorController {
  @GetMapping
  public String handleError() {
    return "forward:/";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
