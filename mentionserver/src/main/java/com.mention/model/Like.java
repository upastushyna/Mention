package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Like {

  @Id
  @Column(name = "like_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", updatable = false)
  @JsonIgnoreProperties(value = {"posts", "sentMessages", "receivedMessages", "comments", "favorites", "chats"})
  private User likedUser;

  @ManyToOne
  @JoinColumn(name = "post_id", updatable = false)
  @JsonIgnoreProperties(value = {"posts", "sentMessages", "receivedMessages", "comments", "favorites", "chats"})
  private Post likedPost;
}
