package com.mention.controller;

import com.mention.model.Favorite;
import com.mention.service002.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

  private FavoriteService favoriteService;

  @Autowired
  public FavoriteController(FavoriteService favoriteService) {
    this.favoriteService = favoriteService;
  }

  @GetMapping("/{id}")
  public Optional<Favorite> getFavorite(@PathVariable Long id) {
    return favoriteService.getFavorite(id);
  }

  @PostMapping
  public void addFavorite(@PathVariable Favorite favorite) {
    favoriteService.addFavorite(favorite);
  }

  @DeleteMapping("/{id}")
  public void deleteFavorite(@PathVariable Long id) {
    favoriteService.deleteFavorite(id);
  }

}
