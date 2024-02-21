package com.panda.back.v2.user.adapter.in.web.dto;

import com.panda.back.v2.user.application.port.in.dto.VerifyEmailDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VerifyEmailReq {

  public final String email;
  private final String verificationCode;

  @Builder
  public VerifyEmailReq(String email, String verificationCode) {
    this.email = email;
    this.verificationCode = verificationCode;
  }

  public VerifyEmailDto toDto() {
    return new VerifyEmailDto(email, verificationCode);
  }
}
