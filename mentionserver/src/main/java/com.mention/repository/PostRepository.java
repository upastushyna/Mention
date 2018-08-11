package com.mention.repository;

import com.mention.model.Post;
import com.mention.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Post findByAuthor_Username(String username);
}
