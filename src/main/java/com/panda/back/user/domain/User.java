package com.panda.back.user.domain;

import com.panda.back.common.exception.user.UnAuthorizedUserException;
import com.panda.back.common.infrastructure.HashingHolder;
import com.panda.back.user.application.port.in.dto.UpdateUserDto;
import com.panda.back.user.application.port.in.dto.CreateUserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

  private final Long id;
  private final String email;
  private final String password;
  private final String nickname;
  private final String profileImgUrl;
  private final UserStatus status;
  private final UserRole role;
  private final Long createdAt;

  @Builder
  public User(Long id, String email, String password, String nickname, String profileImgUrl, UserStatus status,
      UserRole role, Long createdAt) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.profileImgUrl = profileImgUrl;
    this.status = status;
    this.role = role;
    this.createdAt = createdAt;
  }

  public static User from(CreateUserDto createUserDto) {
    return User.builder()
        .email(createUserDto.getEmail())
        .password(createUserDto.getPassword())
        .nickname(createUserDto.getNickname())
        .status(UserStatus.ACTIVE)
        .role(UserRole.NORMAL)
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
        .profileImgUrl(updateUserDto.getProfileImgUrl())
        .status(status)
        .role(role)
        .createdAt(createdAt)
        .build();
  }

  public User delete(String email) {
    if(status.equals(UserStatus.INACTIVE)) {
      throw new UnAuthorizedUserException();
    }
    if (!this.email.equals(email)) {
      throw new UnAuthorizedUserException();
    }
    return User.builder()
        .id(id)
        .email(email)
        .password(password)
        .nickname(nickname)
        .profileImgUrl(profileImgUrl)
        .status(UserStatus.INACTIVE)
        .role(role)
        .createdAt(createdAt)
        .build();
  }
  public User bcryptPassword(HashingHolder hashingHolder) {
    return User.builder()
        .email(email)
        .password(hashingHolder.hashPassword(password))
        .nickname(nickname)
        .status(UserStatus.ACTIVE)
        .role(UserRole.NORMAL)
        .build();
  }
}
