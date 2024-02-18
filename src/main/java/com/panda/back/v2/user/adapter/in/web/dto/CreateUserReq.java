package com.panda.back.v2.user.adapter.in.web.dto;

import com.panda.back.v2.user.applcation.port.in.dto.CreateUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserReq {

  private final String email;
  private final String password;
  private final String nickname;
  
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
