package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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

  @Column(nullable = false, name = "post_body")
  private String body;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnoreProperties(value = {"profile", "posts", "comments", "chats", "favorites"})
  private User author;

  @OneToMany(mappedBy = "post")
  @JsonIgnoreProperties(value = {"author", "favorites"})
  private List<Favorite> favorites;

  @OneToMany(mappedBy = "post")
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
  private String mediafileUrl;

}