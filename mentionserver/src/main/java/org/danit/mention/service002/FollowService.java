package org.danit.mention.service002;

import org.danit.mention.model.Follow;

import java.util.Optional;

public interface FollowService {

  void addFollow(Follow follow);

  void deleteFollow(Long id);

  Optional<Follow> getFollow(Long id);


}
