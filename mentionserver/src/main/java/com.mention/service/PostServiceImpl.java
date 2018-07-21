package com.mention.service;

import com.mention.dao.PostDao;
import com.mention.dao.PostDaoImpl;
import com.mention.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
    PostDaoImpl postDao;

  @Override
    public void addPost(Post post) {
    postDao.addPost(post);
  }

  @Override
    public Post getPost(Long post_id) {
    return postDao.getPost(post_id);
  }

  @Override
    public void updatePost(Post post) {
    postDao.updatePost(post);

  }

  @Override
    public void deletePost(Long post_id) {
    postDao.deletePost(post_id);
  }
}
