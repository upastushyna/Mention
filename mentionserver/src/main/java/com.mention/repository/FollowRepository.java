package com.mention.repository;

import com.mention.dto.UserDtoIdRq;
import com.mention.model.Follow;
import com.mention.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
  void deleteByFollowedUserAndAndFollower(User follower, User followedUser);
}
