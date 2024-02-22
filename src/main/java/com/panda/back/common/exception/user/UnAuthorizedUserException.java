package com.panda.back.common.exception.user;

public class UnAuthorizedUserException extends RuntimeException {

  public UnAuthorizedUserException() {
    super("권한 없는 유저입니다");
  }

}
