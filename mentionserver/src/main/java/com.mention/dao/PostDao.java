package com.mention.dao;

import com.mention.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostDao extends CrudRepository<Post, Long> {

  /*void addPost(Post post);

  Post getPost(Long id);

  void updatePost(Post post);

  void deletePost(Long id);*/

}
