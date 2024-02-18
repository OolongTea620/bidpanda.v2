package com.panda.back.v2.user.applcation.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyEmailDto {

  private final String email;
  private final String verificationCode;
  private final boolean isVerify;

  public VerifyEmailDto(String email, String verificationCode) {
    this.email = email;
    this.verificationCode = verificationCode;
    this.isVerify = false;
  }


}
