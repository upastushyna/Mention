package com.mention.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class HelloMention {
  @GetMapping
  public String hello() {
    return "Hello Mention!";
  }
}