package com.mention.service002;

import com.mention.model.Favorite;

import java.util.Optional;

public interface FavoriteService {

  Optional<Favorite> getFavorite(Long id);

  void addFavorite(Favorite favorite);

  void deleteFavorite(Long id);
}
