package com.panda.back.verification.domain;

import com.panda.back.common.exception.verification.VerifyUserException;
import com.panda.back.common.infrastructure.RandomHolder;
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

  public static Verification generate(String email, RandomHolder randomHolder) {
    return Verification.builder()
        .email(email)
        .verificationCode(randomHolder.random())
        .build();
  }

  public void sendComplete() {
    status = VerificationStatus.Send;
  }

  public void verify(String verificationCodeInput) {
    if (!status.equals(VerificationStatus.Send)) {
      throw new VerifyUserException();
    }
    if (!verificationCode.equals(verificationCodeInput)) {
      throw new VerifyUserException();
    }
    status = VerificationStatus.Done;
  }
}
