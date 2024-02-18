package com.panda.back.v2.user.domain;

import com.panda.back.v2.common.exception.user.UnAuthorizedUserException;
import com.panda.back.v2.user.applcation.port.in.dto.CreateUserDto;
import com.panda.back.v2.user.applcation.port.in.dto.UpdateUserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

  private final Long id;
  private final String email;
  private final String password;
  private final String nickname;
  private final String imageUrl;
  private final UserStatus status;
  private final long createdAt;

  @Builder
  public User(Long id, String email, String password, String nickname, String imageUrl,
      UserStatus status, long createdAt) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.imageUrl = imageUrl;
    this.status = status;
    this.createdAt = createdAt;
  }

  public static User from(CreateUserDto createUserDto) {
    return User.builder()
        .email(createUserDto.getEmail())
        .password(createUserDto.getPassword())
        .nickname(createUserDto.getNickname())
        .status(UserStatus.ACTIVE)
        .build();
  }

  public User modify(UpdateUserDto updateUserDto) {
    if (status.equals(UserStatus.INACTIVE)) {
      throw new UnAuthorizedUserException();
    }
    return User.builder()
        .id(id)
        .email(email)
        .password(password)
        .nickname(updateUserDto.getNickname())
        .imageUrl(updateUserDto.getImageUrl())
        .status(status)
        .createdAt(createdAt)
        .build();
  }

  public User delete(String email) {
    if (this.status.equals(UserStatus.INACTIVE)) {
      throw new UnAuthorizedUserException();
    }
    if (!this.email.equals(email)) {
      throw new UnAuthorizedUserException();
    }
    return User.builder()
        .id(id)
        .email(this.email)
        .password(password)
        .nickname(nickname)
        .imageUrl(getImageUrl())
        .status(UserStatus.INACTIVE)
        .createdAt(createdAt)
        .build();
  }
}
