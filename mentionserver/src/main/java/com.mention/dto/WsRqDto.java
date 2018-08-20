package com.mention.dto;

public class WsRqDto {

  private String name;

  protected WsRqDto() {
  }

  public WsRqDto(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("RqDto{name='%s'}", name);
  }
}
