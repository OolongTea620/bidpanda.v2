package com.panda.back.user.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
  private final String email;
  private String nickname;
  private String profileImgUrl;

  @Builder
  public UpdateUserDto(String email, String nickname, String profileImgUrl) {
    this.email = email;
    this.nickname = nickname;
    this.profileImgUrl = profileImgUrl;
  }

}
