package com.mention.service;

import com.mention.dao.CommentDao;
import com.mention.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

  private CommentDao commentDao;

  @Autowired
  public CommentServiceImpl(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  @Override
  @Transactional
  public void addComment(Comment comment) {
    commentDao.save(comment);
  }

  @Override
  @Transactional
  public void updateComment(Comment comment) {commentDao.save(comment);

  }

  @Override
  @Transactional
  public void deleteComment(Long id) {commentDao.deleteById(id);

  }

  @Override
  public Optional<Comment> getComment(Long id) {
    return commentDao.findById(id);
  }
}
