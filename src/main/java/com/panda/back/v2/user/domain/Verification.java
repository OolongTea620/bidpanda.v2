package com.panda.back.v2.user.domain;

import com.panda.back.v2.common.exception.user.VerifyUserException;
import com.panda.back.v2.common.infrastructure.RandomHolder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Verification {

  private final String email;
  private final String verificationCode;
  private VerificationStatus status;

  @Builder
  public Verification(String email, String verificationCode, VerificationStatus status) {
    this.email = email;
    this.verificationCode = verificationCode;
    this.status = status;
  }

  // 인증 코드 발급
  public static Verification from(String email, RandomHolder randomHolder) {
    return Verification.builder()
        .email(email)
        .verificationCode(randomHolder.random())
        .status(VerificationStatus.GENERATE)
        .build();
  }

  public void setWaiting() {
    if (!status.equals(VerificationStatus.GENERATE)) {
      throw new VerifyUserException();
    }
    this.status = VerificationStatus.WAITING;
  }
  //
  public Verification verifyEmail() {
    return null;
  }


}
