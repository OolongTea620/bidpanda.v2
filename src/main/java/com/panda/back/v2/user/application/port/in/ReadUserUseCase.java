package com.panda.back.v2.user.application.port.in;


public interface ReadUserUseCase {
  boolean existsEmail(String email);
}
