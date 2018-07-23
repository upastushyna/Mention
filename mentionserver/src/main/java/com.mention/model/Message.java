package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
public class Message {

  @Id
  @Column(name = "message_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(nullable = false, name = "message_content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "sender_id", nullable = false)
  @JsonIgnoreProperties(value = "sentMessages")
  private User sender;

  @ManyToOne
  @JoinColumn(name = "receiver_id", nullable = false)
  @JsonIgnoreProperties(value = "receivedMessages")
  private User receiver;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, name = "message_timestamp")
  private Date timestamp;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "message_modify_timestamp")
  private Date modifyTimestamp;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public Date getModifyTimestamp() {
    return modifyTimestamp;
  }

  public void setModifyTimestamp(Date modifyTimestamp) {
    this.modifyTimestamp = modifyTimestamp;
  }
}
