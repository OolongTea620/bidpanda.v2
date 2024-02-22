package com.panda.back.user.adapter.port.in.web.dto;

import com.panda.back.user.application.port.in.dto.CreateUserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateUserReq {
  private final String email;
  private final String password;
  private final String nickname;

  @Builder
  public CreateUserReq(String email, String password, String nickname) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
  }

  public CreateUserDto toDto() {
    return CreateUserDto.builder()
        .email(email)
        .password(password)
        .nickname(nickname)
        .build();
  }
}
