package com.panda.back.common.exception.user;

public class NotFoundUserException extends RuntimeException{

  public NotFoundUserException() {
    super("존재하지 않는 유저입니다");
  }
}
