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

  public void onWaiting() {
    if (!status.equals(VerificationStatus.GENERATE)) {
      throw new VerifyUserException();
    }
    this.status = VerificationStatus.WAITING;
  }
  public Verification verify(String verifyCode) {
    if (!status.equals(VerificationStatus.WAITING)) {
      throw new VerifyUserException();
    } // 메일에 보낸 상태가 아닌 경우
    if (!verificationCode.equals(verifyCode)) {
      throw new VerifyUserException();
    } // 인증코드 미일치
    this.status = VerificationStatus.VERIFIED;
    return this;
  }
}
