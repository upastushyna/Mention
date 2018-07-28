package com.mention.service;

import com.mention.model.Like;

import java.util.Optional;

public interface LikeService {

  Optional<Like> getLike(Long id);

  void addLike(Like like);

  void deleteLike(Long id);
}
