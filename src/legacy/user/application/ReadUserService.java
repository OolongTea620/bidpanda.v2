package com.panda.back.v2.user.application;

import com.panda.back.v2.user.application.port.in.ReadUserUseCase;
import com.panda.back.v2.user.application.port.out.ReadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadUserService implements ReadUserUseCase {
  private final ReadUserPort readUserPort;
  @Override
  public boolean existsEmail(String email) {
    return readUserPort.existsByEmail(email);
  }
}
