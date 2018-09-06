package com.mention.repository;

import com.mention.model.Post;
import com.mention.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Post findByAuthorUsername(String username);

  List<Post> findByBodyContainingIgnoreCase(String body);

  Page<Post> findAllByOrderByTimestampDesc(Pageable pageable);

  Page<Post> findAllByAuthorInOrderByTimestampDesc(List<User> users, Pageable pageable);
}
