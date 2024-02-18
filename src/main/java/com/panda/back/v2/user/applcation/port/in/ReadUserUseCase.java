package com.panda.back.v2.user.applcation.port.in;


public interface ReadUserUseCase {
  boolean existsEmail(String email);
}
