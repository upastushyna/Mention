package com.mention.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;


@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private String commentBody;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User commentCreator;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post commentedPost;

  @Column(name = "post_timestamp")
  private Timestamp timestamp;

  @Column(name = "post_medifileurl")
  private String mediafileUrl;


}
