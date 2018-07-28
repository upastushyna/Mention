package com.mention.controller;

import com.mention.model.Lyke;
import com.mention.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeController {

  private LikeService likeService;

  @Autowired
  public LikeController(LikeService likeService) {
    this.likeService = likeService;
  }

  @PostMapping
  public void addLike(@RequestBody Lyke lyke) {
    likeService.addLike(lyke);
  }

  @GetMapping("/{id}")
  public Optional<Lyke> getLike(@PathVariable Long id) {
    return likeService.getLike(id);
  }

  @DeleteMapping("/{id}")
  public void deleteLike(@PathVariable Long id) {
    likeService.deleteLike(id);
  }
}
