package com.mention.service;

import com.mention.dto.PostLikeRq;

public interface PostLikeService {

  void addPostLike(PostLikeRq postLike);

  void deletePostLike(PostLikeRq postLike);

}
