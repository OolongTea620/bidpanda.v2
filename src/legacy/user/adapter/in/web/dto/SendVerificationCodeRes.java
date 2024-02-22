package com.panda.back.v2.user.adapter.in.web.dto;

import com.panda.back.v2.user.application.port.in.dto.SendVerificationCodeDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SendVerificationCodeRes {

  private final boolean sendSuccess;

  @Builder
  public SendVerificationCodeRes(boolean sendSuccess) {
    this.sendSuccess = sendSuccess;
  }

  public static SendVerificationCodeRes from(SendVerificationCodeDto sendVerificationCodeDto) {
    return SendVerificationCodeRes.builder()
        .sendSuccess(sendVerificationCodeDto.isSendSuccess())
        .build();
  }
}
