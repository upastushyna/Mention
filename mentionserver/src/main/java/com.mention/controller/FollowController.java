package com.mention.controller;

import com.mention.dto.FollowRq;
import com.mention.dto.ShortUserDetailsRs;
import com.mention.service.FollowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  private FollowServiceImpl followService;

  @Autowired
  public FollowController(FollowServiceImpl followService) {
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
  public ResponseEntity<?> addFollow(@RequestBody FollowRq follow) {
    return followService.addFollow(follow);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteFollow(@RequestBody FollowRq follow) {
    return followService.deleteFollow(follow);
  }
}

