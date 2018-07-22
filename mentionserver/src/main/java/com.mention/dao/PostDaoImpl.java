package com.mention.dao;

import com.mention.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Date;

@Repository
public class PostDaoImpl implements PostDao {

  @Autowired
  private EntityManager entityManager;


  @Override
  public void addPost(Post post) {
    post.setPost_timestamp(new Timestamp(new Date().getTime()));
    entityManager.persist(post);
  }

  @Override
  public Post getPost(Long post_id) {
    return entityManager.find(Post.class, post_id);
  }

  @Override
  public void updatePost(Post post) {
    entityManager.merge(post);
  }

  @Override
  public void deletePost(Long post_id) {
    Post post = entityManager.find(Post.class, post_id);
    entityManager.remove(post);
  }
}
