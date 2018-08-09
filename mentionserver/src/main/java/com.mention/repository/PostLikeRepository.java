package com.mention.repository;

import com.mention.model.PostLike;
import com.mention.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

  Optional<PostLike> findByUser(String author);

}
