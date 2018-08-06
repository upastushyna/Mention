package com.mention.service002;

import com.mention.model.PostComment;
import com.mention.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;

  @Autowired
  public CommentServiceImpl(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  @Transactional
  public void addComment(PostComment postComment) {
    commentRepository.save(postComment);
  }

  @Override
  @Transactional
  public void updateComment(PostComment postComment) {
    commentRepository.save(postComment);

  }

  @Override
  @Transactional
  public void deleteComment(Long id) {
    commentRepository.deleteById(id);

  }

  @Override
  public Optional<PostComment> getComment(Long id) {
    return commentRepository.findById(id);
  }
}
