package com.mention.controller;

import com.mention.dto.FollowDtoRq;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.model.Follow;
import com.mention.service.UserFollowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

  private UserFollowServiceImpl followService;

  @Autowired
  public FollowController(UserFollowServiceImpl followService) {
    this.followService = followService;
  }

  @GetMapping("/followed/{username}")
  List<ShortUserDetailsRs> getFollowedUsers(@PathVariable String username) {
    return followService.getFollowedUsers(username);
  }

  @GetMapping("/following/{username}")
  List<ShortUserDetailsRs> getFollowingUsers(@PathVariable String username) {
    return followService.getFollowingUsers(username);
  }

  @PostMapping("/add")
  public void addFollow(@RequestBody FollowDtoRq follow) {
    followService.addFollow(follow);
  }

  @DeleteMapping("/delete")
  public void deleteFollow(@RequestBody FollowDtoRq follow) {
    followService.removeFollow(follow);
  }
}
