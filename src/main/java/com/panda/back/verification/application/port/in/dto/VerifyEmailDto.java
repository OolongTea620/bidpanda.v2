package com.panda.back.verification.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VerifyEmailDto {
  private final String email;
  private final String verificationCode;
  private final boolean isVerified;

  @Builder
  public VerifyEmailDto(String email, String verificationCode, boolean isVerified) {
    this.email = email;
    this.verificationCode = verificationCode;
    this.isVerified = isVerified;
  }
}
