package com.panda.back.v2.user.applcation;

import com.panda.back.v2.user.applcation.port.in.ReadUserUseCase;
import com.panda.back.v2.user.applcation.port.out.ReadUserPort;
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
