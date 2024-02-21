package com.panda.back.v2.user.adapter.out.redis.entity;

import com.panda.back.v2.user.domain.Verification;
import com.panda.back.v2.user.domain.VerificationStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VerificationEntity {
  private final String email;
  private final String verificationCode;
  @Builder
  public VerificationEntity(String email, String verificationCode) {
    this.email = email;
    this.verificationCode = verificationCode;
  }

  public static VerificationEntity from(String email, String verificationCode) {
    return VerificationEntity.builder()
        .email(email)
        .verificationCode(verificationCode)
        .build();
  }

  public static VerificationEntity from(Verification verification) {
    return VerificationEntity.builder()
        .email(verification.getEmail())
        .verificationCode(verification.getVerificationCode())
        .build();
  }
  public Verification toModel() {
    return Verification.builder()
        .email(email)
        .verificationCode(verificationCode)
        .status(VerificationStatus.WAITING)
        .build();
  }
}
