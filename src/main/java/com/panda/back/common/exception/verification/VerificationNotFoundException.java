package com.panda.back.common.exception.verification;

public class VerificationNotFoundException extends RuntimeException{
  public VerificationNotFoundException() {
    super("인증 여부를 찾을 수 없습니다");
  }
}
