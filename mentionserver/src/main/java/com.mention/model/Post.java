package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Post {
  @Id
  @Column(name = "post_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "post_body")
  private String body;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"profile", "posts", "comments", "chats", "favorites"})
  private User author;

  @Column(name = "post_amazon_key")
  private String amazonKey;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  @JsonIgnoreProperties(value = {"post"})
  private List<Comment> comments;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "post_timestamp", updatable = false)
  private Date timestamp;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "post_modify_timestamp")
  private Date modifyTimestamp;

  @Column(name = "post_mediafile_url")
  private String mediaFileUrl;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  @JsonIgnoreProperties(value = {"post"})
  private List<PostLike> postLikes;

  @ManyToOne
  @JoinColumn(name = "post_parent_id",  updatable = false)
  @JsonIgnoreProperties(value = {"children"})
  private Post parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  @JsonIgnoreProperties(value = {"parent"})
  private List<Post> children;

  protected Post(){}

  public Post(String body, User author) {
    this.body = body;
    this.author = author;
  }
}