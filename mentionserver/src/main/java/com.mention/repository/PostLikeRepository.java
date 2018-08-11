package com.mention.repository;

import com.mention.model.PostLike;
import com.mention.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

  PostLike findByUser(User author);
  
  PostLike findByUserUsername(String username);
  
}
