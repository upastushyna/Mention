package com.mention.controller;

import com.mention.model.Likes;
import com.mention.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/like")
public class LikesController {

  private LikesService likesService;

  @Autowired
  public LikesController(LikesService likesService){
    this.likesService = likesService;
  }

  @PostMapping
  public void addLike(@PathVariable Likes likes){
    likesService.addLike(likes);
  }

  @GetMapping("/{id}")
  public Optional<Likes> getLike(@PathVariable Long id){
    return likesService.getLike(id);
  }

  @DeleteMapping("/{id}")
  public void deleteLike(@PathVariable Long id){
    likesService.deleteLike(id);
  }

}
