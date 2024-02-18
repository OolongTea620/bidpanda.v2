package com.panda.back.v2.user.adapter.in.web.dto;

import com.panda.back.v2.user.applcation.port.in.dto.VerifyEmailDto;
import lombok.Getter;

@Getter
public class VerifyEmailRes {

  private final boolean isVerified;

  public VerifyEmailRes(boolean isVerified) {
    this.isVerified = isVerified;
  }

  public static VerifyEmailRes from (VerifyEmailDto verifyEmailDto) {
    return new VerifyEmailRes(verifyEmailDto.isVerify());
  }

}
