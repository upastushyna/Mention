package com.mention.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/x")
public class XController {

  @GetMapping
  String get() {
    return "OK";
  }

}
