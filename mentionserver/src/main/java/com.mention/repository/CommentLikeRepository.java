package com.mention.repository;

import com.mention.model.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
  void deleteByUserIdAndCommentId(Long userId, Long commentId);
}
