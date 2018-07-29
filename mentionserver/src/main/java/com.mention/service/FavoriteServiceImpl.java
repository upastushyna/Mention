package com.mention.service;

import com.mention.repository.FavoriteRepository;
import com.mention.model.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

  private FavoriteRepository favoriteRepository;

  @Autowired
  public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
    this.favoriteRepository = favoriteRepository;
  }

  @Override
  public Optional<Favorite> getFavorite(Long id) {
    return favoriteRepository.findById(id);
  }

  @Override
  @Transactional
  public void addFavorite(Favorite favorite) {
    favoriteRepository.save(favorite);
  }

  @Override
  @Transactional
  public void deleteFavorite(Long id) {
    favoriteRepository.deleteById(id);
  }
}
