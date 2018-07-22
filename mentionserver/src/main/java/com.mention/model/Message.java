package com.mention.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(nullable = false, name = "MESSAGE_CONTENT")
  private String content;

  @ManyToOne
  @JoinColumn(name = "SENDER_ID", nullable = false)
  @JsonIgnoreProperties(value = "sentMessages")
  private User sender;

  @ManyToOne
  @JoinColumn(name = "RECEIVER_ID", nullable = false)
  @JsonIgnoreProperties(value = "receivedMessages")
  private User receiver;

  @Column(nullable = false, name = "MESSAGE_TIMESTAMP")
  private Timestamp timestamp;

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

  public Timestamp getMessage_timestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
