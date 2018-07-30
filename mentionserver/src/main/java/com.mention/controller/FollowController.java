package com.mention.controller;

import com.mention.model.Follow;
import com.mention.service002.FollowService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/follow")
public class FollowController {

  private FollowService followService;

  public FollowController(FollowService followService) {
    this.followService = followService;
  }

  @PostMapping
  public void addFollow(@RequestBody Follow follow) {
    followService.addFollow(follow);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteFollow(@PathVariable Long id) {
    followService.deleteFollow(id);
  }

  @GetMapping(value = "/{id}")
  public Optional<Follow> getFollow(@PathVariable Long id) {
    return followService.getFollow(id);
  }
}
