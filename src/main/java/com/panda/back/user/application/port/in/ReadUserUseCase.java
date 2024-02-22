package com.panda.back.user.application.port.in;

public interface ReadUserUseCase {
  boolean existsUser(String email);
}
