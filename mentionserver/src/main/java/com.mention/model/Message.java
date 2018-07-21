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
  private int message_id;

  @Column(nullable = false)
  private String message_content;

  @ManyToOne
  @JoinColumn(name = "SENDER_ID", nullable = false)
  @JsonIgnoreProperties(value = "user_sentMessages")
  private User message_sender;

  @ManyToOne
  @JoinColumn(name = "RECEIVER_ID", nullable = false)
  @JsonIgnoreProperties(value = "user_receivedMessages")
  private User message_receiver;

  @Column
  private Timestamp message_timestamp;

  public int getMessage_id() {
    return message_id;
  }

  public void setMessage_id(int message_id) {
    this.message_id = message_id;
  }

  public String getMessage_content() {
    return message_content;
  }

  public void setMessage_content(String message_content) {
    this.message_content = message_content;
  }

  public User getMessage_sender() {
    return message_sender;
  }

  public void setMessage_sender(User message_sender) {
    this.message_sender = message_sender;
  }

  public User getMessage_receiver() {
    return message_receiver;
  }

  public void setMessage_receiver(User message_receiver) {
    this.message_receiver = message_receiver;
  }

  public Timestamp getMessage_timestamp() {
    return message_timestamp;
  }

  public void setMessage_timestamp(Timestamp message_timestamp) {
    this.message_timestamp = message_timestamp;
  }
}
