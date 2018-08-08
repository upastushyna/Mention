package org.danit.mention.repository;

import org.danit.mention.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Post findByAuthor_Username(String username);

}
