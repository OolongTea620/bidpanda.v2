package com.panda.back.v2.common.exception.user;

public class VerifyUserException extends RuntimeException{
  public VerifyUserException() {
    super("사용자 인증에 실패 했습니다.");
  }
}
