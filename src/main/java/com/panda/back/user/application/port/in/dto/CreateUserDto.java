package com.panda.back.user.application.port.in.dto;

import com.panda.back.user.domain.User;
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

  public static CreateUserDto from(User user) {
    return CreateUserDto.builder()
        .email(user.getEmail())
        .nickname(user.getNickname())
        .createdAt(user.getCreatedAt())
        .build();
  }

}
