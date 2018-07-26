package com.mention.controller;

import com.mention.model.Like;
import com.mention.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/like")
public class LikeController {

  private LikeService likeService;

  @Autowired
  public LikeController(LikeService likeService){
    this.likeService = likeService;
  }

  @PostMapping
  public void addLike(@RequestBody Like like){
    likeService.addLike(like);
  }

  @GetMapping(value = "/{id}")
  public Optional<Like> getLike(@PathVariable Long id){
    return likeService.getLike(id);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteLike(@PathVariable Like like){
    likeService.deleteLike(like);
  }

}
