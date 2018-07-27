package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
public class Follow {

  @Id
  @Column(name = "fw_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "fw_follower")
  @JsonIgnoreProperties(value =
          {"profile", "sentMessages", "posts", "comments", "receivedMessages", "chats", "favorites", "followers", "signers"})
  private User follower;

  @ManyToOne
  @JoinColumn(name = "fw_signer")
  @JsonIgnoreProperties(value =
          {"profile", "sentMessages", "posts", "comments", "receivedMessages", "chats", "favorites", "followers", "signers"})
  private User signer;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "fw_timestamp", updatable = false)
  private Date timestamp;


}