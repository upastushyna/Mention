package com.mention.service002;

import com.mention.model.PostLike;

import java.util.Optional;

public interface LikeService {

  Optional<PostLike> getLike(Long id);

  void addLike(PostLike postLike);

  void deleteLike(Long id);

}
