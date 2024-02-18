package com.panda.back.v2.user.adapter.in.web.dto;

import com.panda.back.v2.user.applcation.port.in.dto.CreateUserDto;
import com.panda.back.v2.user.domain.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.CloseableThreadContext.Instance;

@Getter
@Setter
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

  public static CreateUserRes from (CreateUserDto createUserDto) {
    return CreateUserRes.builder()
        .email(createUserDto.getEmail())
        .nickname(createUserDto.getNickname())
        .createdAt(LocalDateTime
            .ofInstant(Instant.ofEpochMilli(createUserDto.getCreatedAt()),ZoneId.systemDefault()))
        .build();
  }
  public static CreateUserRes from (User user) {
    return CreateUserRes.builder()
        .email(user.getEmail())
        .nickname(user.getNickname())
        .createdAt(LocalDateTime
            .ofInstant(Instant.ofEpochMilli(user.getCreatedAt()),ZoneId.systemDefault()))
        .build();
  }

}
