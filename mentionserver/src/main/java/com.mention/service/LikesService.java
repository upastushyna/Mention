package com.mention.service;

import com.mention.model.Likes;

import java.util.Optional;

public interface LikesService {

  Optional<Likes> getLike(Long id);

  void addLike(Likes likes);

  void deleteLike(Long id);
}
