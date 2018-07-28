package com.mention.service;

import com.mention.model.Lyke;

import java.util.Optional;

public interface LikeService {

  Optional<Lyke> getLike(Long id);

  void addLike(Lyke lyke);

  void deleteLike(Long id);

}
