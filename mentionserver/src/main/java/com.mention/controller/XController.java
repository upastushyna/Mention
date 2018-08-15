package com.mention.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XController {

  @GetMapping("/")
  String getRoot() {
    return "ROOT";
  }

  @GetMapping("/xu")
  String getUnsecured() {
    return "unsecured";
  }

  @GetMapping("/xs")
  String getSecured() {
    return "secured";
  }

}
