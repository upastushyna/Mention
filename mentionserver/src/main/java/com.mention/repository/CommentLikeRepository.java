package com.mention.repository;

import com.mention.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<PostLike, Long> {
}
