package org.danit.mention.service002;

import org.danit.mention.model.PostLike;

import java.util.Optional;

public interface LikeService {

  Optional<PostLike> getLike(Long id);

  void addLike(PostLike postLike);

  void deleteLike(Long id);

}
