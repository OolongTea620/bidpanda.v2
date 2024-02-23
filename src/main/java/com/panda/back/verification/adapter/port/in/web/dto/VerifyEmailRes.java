package com.panda.back.verification.adapter.port.in.web.dto;

import com.panda.back.verification.application.port.in.dto.VerifyEmailDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VerifyEmailRes {
  private boolean isverified;
  @Builder
  public VerifyEmailRes(boolean isverified) {
    this.isverified = isverified;
  }

  public static VerifyEmailRes from(VerifyEmailDto verifyEmailDto) {
    return VerifyEmailRes.builder()
        .isverified(verifyEmailDto.isVerified())
        .build();
  }
}
