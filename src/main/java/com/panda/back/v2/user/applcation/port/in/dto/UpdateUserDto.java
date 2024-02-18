package com.panda.back.v2.user.applcation.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {

  private String nickname;
  private String imageUrl;

  @Builder
  public UpdateUserDto(String nickname, String imageUrl) {
    this.nickname = nickname;
    this.imageUrl = imageUrl;
  }
}
