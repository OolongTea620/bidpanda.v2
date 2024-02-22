package com.panda.back.v2.user.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendVerificationCodeDto {

  private String email;
  private boolean sendSuccess;

  @Builder
  public SendVerificationCodeDto(String email, boolean sendSuccess) {
    this.email = email;
    this.sendSuccess = sendSuccess;
  }
  public SendVerificationCodeDto(String email) {
    this.email = email;
    this.sendSuccess = false;
  }

}
