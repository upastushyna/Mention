package com.mention.service;

import com.mention.dto.PostLikeRq;
import org.springframework.http.ResponseEntity;

public interface PostLikeService {

  ResponseEntity<?> addPostLike(PostLikeRq postLike);

  ResponseEntity<?> deletePostLike(PostLikeRq postLike);

}
