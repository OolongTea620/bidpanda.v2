package com.panda.back.v2.user.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

  private String email;
  private String password;
  private String nickname;
  private Long createdAt;

  @Builder
  public CreateUserDto(String email, String password, String nickname, Long createdAt) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.createdAt = createdAt;
  }
}
