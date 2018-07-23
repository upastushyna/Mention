package com.mention.dao;

import com.mention.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PostDaoImpl implements PostDao {

  @Autowired
  private EntityManager entityManager;


  @Override
  public void addPost(Post post) {
    entityManager.persist(post);
  }

  @Override
  public Post getPost(Long id) {
    return entityManager.find(Post.class, id);
  }

  @Override
  public void updatePost(Post post) {
    entityManager.merge(post);
  }

  @Override
  public void deletePost(Long id) {
    Post post = entityManager.find(Post.class, id);
    entityManager.remove(post);
  }
}
