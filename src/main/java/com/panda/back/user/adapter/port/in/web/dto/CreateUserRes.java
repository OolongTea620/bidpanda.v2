package com.panda.back.user.adapter.port.in.web.dto;

import com.panda.back.user.application.port.in.dto.CreateUserDto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateUserRes {

  private final String email;
  private final String nickname;
  private final LocalDateTime createdAt;

  @Builder
  public CreateUserRes(String email, String nickname, LocalDateTime createdAt) {
    this.email = email;
    this.nickname = nickname;
    this.createdAt = createdAt;
  }

  public static CreateUserRes from(CreateUserDto createUserDto) {
    return CreateUserRes.builder()
        .email(createUserDto.getEmail())
        .nickname(createUserDto.getNickname())
        .createdAt(LocalDateTime
            .ofInstant(Instant.ofEpochMilli(createUserDto.getCreatedAt()), ZoneId.systemDefault()))
        .build();
  }

}
