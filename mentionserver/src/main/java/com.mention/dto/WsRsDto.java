package com.mention.dto;

public class WsRsDto {

  private String content;
  private static int id;

  public WsRsDto() {
  }

  public WsRsDto(String content) {
    this.content = content;
    id++;
  }

  public String getContent() {
    return content;
  }

  public int getId() {
    return id;
  }
}
