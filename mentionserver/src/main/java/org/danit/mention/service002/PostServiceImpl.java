package org.danit.mention.service002;

import org.danit.mention.repository.PostRepository;
import org.danit.mention.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  @Transactional
  public void addPost(Post post) {
    postRepository.save(post);
  }

  @Override
  public Optional<Post> getPost(Long id) {
    return postRepository.findById(id);
  }

  @Override
  @Transactional
  public void updatePost(Post post) {
    postRepository.save(post);
  }

  @Override
  @Transactional
  public void deletePost(Long id) {
    postRepository.deleteById(id);
  }
}
