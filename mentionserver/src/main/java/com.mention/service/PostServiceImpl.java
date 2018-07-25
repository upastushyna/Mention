package com.mention.service;

import com.mention.dao.PostDao;
import com.mention.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

  private PostDao postDao;

  @Autowired
  public PostServiceImpl(PostDao postDao) {
    this.postDao = postDao;
  }

  @Override
  @Transactional
  public void addPost(Post post) {
    postDao.save(post);
  }

  @Override
  public Optional<Post> getPost(Long id) {
    return postDao.findById(id);
  }

  @Override
  @Transactional
  public void updatePost(Post post) {
    postDao.save(post);
  }

  @Override
  @Transactional
  public void deletePost(Long id) {
    postDao.deleteById(id);
  }
}
