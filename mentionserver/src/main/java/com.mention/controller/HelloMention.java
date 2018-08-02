package com.mention.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hellomention")

public class HelloMention {

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloMention.class);

  @GetMapping
  public String hello() {
    LOGGER.info("Hello my Dear Friend");
    return "Hello Mention!";
  }
}