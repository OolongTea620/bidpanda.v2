package com.panda.back.v2.common.exception.user;

public class AlreadyExistsUserException extends RuntimeException{

  public AlreadyExistsUserException() {
    super("이미 존재하는 유저입니다");
  }
}
