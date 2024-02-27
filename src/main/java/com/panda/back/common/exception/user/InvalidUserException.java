package com.panda.back.common.exception.user;

public class InvalidUserException extends RuntimeException{

  public InvalidUserException() {
    super("잘못된 동작입니다");
  }
}
